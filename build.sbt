lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """play-scala-starter-example""",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.6",
      libraryDependencies ++= Seq(
      guice,
      "com.h2database" % "h2" % "1.4.199",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,

        // Db connection
      "mysql" % "mysql-connector-java" % "8.0.25",
      "com.typesafe.play" %% "play-slick" % "5.0.0",
      "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",

         // Async work
      "org.scala-lang.modules" %% "scala-async" % "0.10.0",
       "org.scala-lang" % "scala-reflect" % scalaVersion.value % Provided,

        // testing
        "org.scalatestplus" %% "mockito-3-4" % "3.2.9.0" % "test",

    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )
