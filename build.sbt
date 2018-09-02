name := "challenges"

version := "0.1"

scalaVersion := "2.12.6"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

wartremoverErrors ++= Warts.unsafe

libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.6"
