import Dependencies._

ThisBuild / scalaVersion     := "2.12.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"

lazy val root = project
  .in(file("."))
  .settings(
    name := "graphdatastructures",
    settings,
    libraryDependencies ++= commonDependencies)
  .aggregate(core,benchmark)

lazy val core = project
  .settings(
    name := "core",
    settings,
    libraryDependencies ++= commonDependencies)

lazy val benchmark = project
  .settings(
    name := "benchmark",
    settings,
    libraryDependencies ++=
      (commonDependencies ++
      benchmarkDependencies)
  ).dependsOn(core)

// scalafmtOnCompile := true
//
//lazy val settings =  scalafmtSettings
//
//lazy val scalafmtSettings = Seq(
//  scalafmtOnCompile := true
//)


lazy val commonDependencies = Seq(
  scalatest % Test,
  scopt,
  logbackClassic,
  scalalog,
  enum
)

lazy val benchmarkDependencies = Seq()

import scalariform.formatter.preferences._

val settings =   {
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
//    setPreference(DoubleIndentMethodDeclaration, false).
    setPreference(FormatXml, true).
    setPreference(IndentLocalDefs, true).
    setPreference(IndentPackageBlocks, true).
    setPreference(IndentSpaces, 2).
    setPreference(MultilineScaladocCommentsStartOnFirstLine, false).
//    setPreference(PreserveSpaceBeforeArguments, true).
    setPreference(RewriteArrowSymbols, false)
//    setPreference(SpaceBeforeColon, false).
//    setPreference(SpaceInsideBrackets, false).
//    setPreference(SpaceInsideParentheses, false).
//    setPreference(SpacesWithinPatternBinders, true)
}