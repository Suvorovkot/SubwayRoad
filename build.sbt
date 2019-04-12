enablePlugins(JavaAppPackaging, DockerPlugin)

name := "SubwayRoad"

version := "0.1"

scalaVersion := "2.12.8"

dockerBaseImage := "openjdk:jre-alpine"

import com.typesafe.sbt.packager.docker._
dockerCommands ++= Seq(
  Cmd("USER", "root"),
  ExecCmd("RUN", "apk", "add", "--no-cache", "bash")
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.json4s" %% "json4s-jackson" % "3.6.5",
  "com.typesafe.akka" %% "akka-actor" % "2.5.21",
  "com.typesafe.akka" %% "akka-stream" % "2.5.21",
  "com.typesafe.akka" %% "akka-http" % "10.1.7"
)

libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4"
)