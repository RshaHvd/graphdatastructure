import Dependencies._

ThisBuild / scalaVersion     := "2.12.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"

lazy val root = project
  .in(file("."))
  .settings(
    name := "graphdatastructures",
    settings,
    libraryDependencies ++= commonDependencies)
  .aggregate(core)

lazy val core = project
  .settings(
    name := "core",
    settings,
    libraryDependencies ++= commonDependencies)

scalafmtOnCompile := true

lazy val settings =
    scalafmtSettings

lazy val scalafmtSettings = Seq(
  scalafmtOnCompile := true
)


lazy val commonDependencies = Seq(
  scalaTest % Test
)

