import sbt._
import Keys._

object LearningBuild extends Build {
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
        "org.specs2" % "specs2-core_2.11" % "3.8.4" % "test"
      ),
      scalaVersion := "2.11.7"
    )
  )
}