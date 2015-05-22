name := """Products"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(play.PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs
)


fork in run := true