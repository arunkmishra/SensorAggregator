package processor

import model.FailedAggregation
import model.OutputData
import model.ValidAggregation
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import scala.collection.immutable.HashMap

class CsvProcessorSpec extends AnyFlatSpec with should.Matchers {

  private val directoryName = getClass.getResource("/validSensorData").getPath

  val defaultOutput: OutputData = OutputData(
    HashMap(
      "s1" -> ValidAggregation(10, 98, 108, 2),
      "s2" -> ValidAggregation(78, 88, 246, 3),
      "s3" -> FailedAggregation
    ),
    2,
    7,
    2
  )

  val processor = new ProcessorImpl()
  private def fixNewLine(s: String) = s.replaceAll("\\r\\n|\\r|\\n", "\\n")

  "Output of default test" should "be as specified in task.md including string representation" in {
    val outputData = processor.run(directoryName)
    outputData should be(defaultOutput)

    fixNewLine(outputData.toString) should be(fixNewLine("""Num of processed files: 2
                                                           |Num of processed measurements: 7
                                                           |Num of failed measurements: 2
                                                           |
                                                           |Sensors with highest avg humidity:
                                                           |
                                                           |sensor-id,min,avg,max
                                                           |s2,78,82,88
                                                           |s1,10,54,98
                                                           |s3,NaN,NaN,NaN""".stripMargin))
  }

  "Output data" should "be empty" in {
    val directoryName = getClass.getResource("/invalidData").getPath
    val outputData = processor.run(directoryName)
    val expectedOutput: OutputData = OutputData(
      HashMap(
        "s1" -> ValidAggregation(10, 10, 10, 1),
        "s2" -> FailedAggregation,
        "s3" -> ValidAggregation(20, 20, 20, 1)
      ),
      1,
      3,
      1
    )
    outputData should be(expectedOutput)
  }

}
