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
  ).dependsOn(core)


lazy val commonDependencies = Seq(
  scalatest % Test,
  scopt,
  logbackClassic,
  scalalog,
  enum
)

lazy val benchmarkDependencies = Seq(
  csv
)

import scalariform.formatter.preferences._

val formatSettings =   {
  scalariformPreferences := formattingPreferences
}

val formattingPreferences: IFormattingPreferences = {
  import scalariform.formatter.preferences._
  FormattingPreferences().
    setPreference(AlignParameters, true).
    setPreference(AlignSingleLineCaseStatements, true).
    setPreference(CompactControlReadability, true).
    setPreference(CompactStringConcatenation, false).
    setPreference(DanglingCloseParenthesis, Preserve).
    setPreference(FormatXml, true).
    setPreference(IndentLocalDefs, true).
    setPreference(IndentPackageBlocks, true).
    setPreference(IndentSpaces, 2).
    setPreference(MultilineScaladocCommentsStartOnFirstLine, false).
    setPreference(PreserveSpaceBeforeArguments, true).
    setPreference(RewriteArrowSymbols, false)
//    setPreference(SpaceBeforeColon, false).
//    setPreference(SpaceInsideBrackets, false).
//    setPreference(SpaceInsideParentheses, false).
//    setPreference(SpacesWithinPatternBinders, true)
}