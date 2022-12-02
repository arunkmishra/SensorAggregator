import processor.SequentialProcessor

object Main {

  def main(args: Array[String]): Unit = {
    println(">>> running app")
    val csvProcessor = new SequentialProcessor()
    println("process")
    val result = csvProcessor.run(args.headOption.getOrElse("."))
    println(result)
  }
}
