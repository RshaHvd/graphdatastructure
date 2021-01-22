import Dependencies._
import sbt._
import sbt.Keys.{target, _}
import sbtassembly.AssemblyPlugin.autoImport.{assemblyJarName, assemblyOption}

ThisBuild / scalaVersion     := "2.12.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"

lazy val root = project
  .in(file("."))
  .settings(
    name := "graphdatastructures",
    formatSettings,
    libraryDependencies ++= commonDependencies)
  .aggregate(core,benchmark)

lazy val core = project
  .settings(
    name := "core",
    formatSettings,
    libraryDependencies ++= commonDependencies)

lazy val benchmark = project
  .settings(
    name := "benchmark",
    formatSettings,
    mainClass in assembly := Some("hvd.edu.benchmark.BenchmarkEngine"),
    assemblyJarName in assembly := "benchmark.jar",
    assemblyOutputPath in assembly :=  ((target in Compile).value / "scala" /  (assemblyJarName in assembly).value),
    libraryDependencies ++= (
      commonDependencies ++
      benchmarkDependencies)
  ).dependsOn(core).enablePlugins(JmhPlugin)


lazy val commonDependencies = Seq(
  scalatest % Test,
  scopt,
  logbackClassic,
  scalalog,
  enum,
  jolJava
)

lazy val benchmarkDependencies = Seq(
  csv
)

val formatSettings =   {
  scalariformPreferences := Format.formattingPreferences
}