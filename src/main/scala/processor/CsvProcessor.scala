package processor

import com.github.tototoshi.csv.CSVReader
import model.Measurement
import model.OutputData

import java.io.File

trait CsvProcessor {
  /** Read and aggregate data from a sequence of CSV-files.
    *
    * @param directoryName
    *   name of input directory
    * @return
    *   processed data from all input files.
    */
  def run(directoryName: String): OutputData

  /** Retrieve list of CSV-files from specified directory.
    *
    * @param directoryName
    *   name of input directory
    * @return
    *   list of CSV-files.
    */
  def getInputFiles(directoryName: String): Seq[File] = {
    val inputDirectory = new File(directoryName)
    if (!inputDirectory.isDirectory) throw new IllegalArgumentException("Wrong input directory")
    inputDirectory.listFiles((_, name) => name.endsWith(".csv")).toIndexedSeq
  }

  /** Process a CSV-file.
    *
    * @param file
    *   a file to process
    * @param outputData
    *   optional instance of [[model.OutputData]], which contains data from previously processed files.
    * @return
    *   a new [[model.OutputData]] instance containing results of current file processing and optionally from previously
    *   processed files.
    */
  protected def processCsvFile(file: File, outputData: OutputData = OutputData()): OutputData = {
    val csvFile = CSVReader.open(file)

    val fileOutputData = csvFile.toStream.tail
      .flatMap { // Parse a line
        case id :: "NaN" :: Nil    => Some(Measurement(id, None))
        case id :: humidity :: Nil => Some(Measurement(id, humidity.toIntOption))
        case _ =>
          println(s"Not supported row found in file '${file.getName}'")
          None
      }
      .foldLeft(outputData)(_ + _)

    fileOutputData.copy(fileCount = fileOutputData.fileCount + 1)
  }
}
