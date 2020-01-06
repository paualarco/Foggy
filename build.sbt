import Dependencies._

//enablePlugins(JavaAppPackaging, DockerPlugin, PlayScala)

mainClass in Compile := Some("com.cloriko.WebServer")

PB.protocVersion := "-v3.10.0"

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "com.cloriko",
      scalaVersion := "2.12.4",
      version      := Version.version
    )),
    name := "cloriko",
    libraryDependencies ++= ProjectDependencies
  )
  .aggregate(frontend, master)

lazy val frontend = (project in file("frontend"))
  .settings(
    name := "cloriko-frontend",
    libraryDependencies ++= FrontendDependencies,
    version := Version.version
  )
  .enablePlugins(JavaAppPackaging, DockerPlugin, PlayScala)

lazy val common = (project in file("common"))
  .settings(
    name := "cloriko-common",
    libraryDependencies ++= ProjectDependencies,
    version := Version.version
  )
  .dependsOn(frontend % "compile->compile;test->test")
  .enablePlugins(JavaAppPackaging, DockerPlugin)

lazy val master = (project in file("master"))
  .settings(
    name := "cloriko-master",
    libraryDependencies ++= ProjectDependencies,
    version := Version.version
  )
  .dependsOn(frontend % "compile->compile;test->test", common % "compile->compile;test->test")
  .enablePlugins(JavaAppPackaging, DockerPlugin)

lazy val slave = (project in file("slave"))
  .settings(
    name := "cloriko-slave",
    libraryDependencies ++= ProjectDependencies,
    version := Version.version
  )
  .dependsOn(common % "compile->compile;test->test", master % "compile->compile;test->test")
  .enablePlugins(JavaAppPackaging, DockerPlugin)




