import sbt._

object Dependencies {
  lazy val scalatest = "org.scalatest" %% "scalatest" % "3.0.8"
  lazy val scopt = "com.github.scopt" %% "scopt" % "3.7.1"
  lazy val enum =  "com.beachape" %% "enumeratum" % "1.6.1"
  lazy val logbackClassic = "ch.qos.logback" % "logback-classic" % "1.2.3"
  lazy val scalalog = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
}
