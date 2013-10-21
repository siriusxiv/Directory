name := "Directory"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.18",
  "com.typesafe" %% "play-plugins-mailer" % "2.1.0",
  javaJdbc,
  javaEbean,
  cache
)     

play.Project.playJavaSettings
