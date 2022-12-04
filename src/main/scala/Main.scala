import processor.ProcessorImpl

object Main {

  def main(args: Array[String]): Unit = {
    val csvProcessor = new ProcessorImpl()
    val result = csvProcessor.run(args.headOption.getOrElse("."))
    println(result)
  }
}
