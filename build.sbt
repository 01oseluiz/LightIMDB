name := "LightIMDB"

version := "1.0"

lazy val `lightimdb` = (project in file(".")).enablePlugins(PlayScala)


scalaVersion := "2.11.7"

libraryDependencies += filters

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0" % Test

libraryDependencies += evolutions

//libraryDependencies += jdbc

libraryDependencies += "com.typesafe.play" %% "anorm" % "2.5.1"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.27"

libraryDependencies += "org.webjars" % "bootstrap" % "3.3.7"

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test )


unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  