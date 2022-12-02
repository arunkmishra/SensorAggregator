package processor

import model.OutputData

/** Sequential processing of CSV files.
  *
  * Slow, but minimal memory requirements.
  */
class SequentialProcessor extends CsvProcessor {
  /** Read and aggregate data from a sequence of CSV-files.
    *
    * @param directoryName
    *   name of input directory
    * @return
    *   processed data from all input files.
    */
  override def run(directoryName: String): OutputData =
    getInputFiles(directoryName).foldLeft(OutputData())((either, file) => processCsvFile(file, either))

}
