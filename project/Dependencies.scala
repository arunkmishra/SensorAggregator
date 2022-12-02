import sbt._
object Dependencies {

	private val scalaCsvVersion = "1.3.10"
	private val scalaTestVersion = "3.2.14"

	val scalaCsv = "com.github.tototoshi" %% "scala-csv" % scalaCsvVersion
	val scalaTest =  "org.scalatest" %% "scalatest" % scalaTestVersion
}
