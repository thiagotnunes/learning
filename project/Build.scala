import sbt._
import Keys._

object Build extends Build {
  lazy val root = Project(
    "learning",
    base = file("."),
    settings = Seq(
      organization := "com.learning",
      scalacOptions ++= Seq(
        "-deprecation",
        "-unchecked",
        "-language:_",
        "-feature"
      ),
      name := "learning",
      version := "0.0.1",
      libraryDependencies ++= Seq(
        "org.specs2" %% "specs2-core" % "3.8.4" % "test",
        "org.specs2" %% "specs2-scalacheck" % "3.8.6"
      ),
      scalaVersion := "2.11.7"
    )
  )
}