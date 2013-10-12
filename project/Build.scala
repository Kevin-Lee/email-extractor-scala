import sbt._
import Keys._
import play.Project._

import com.github.play2war.plugin._

object ApplicationBuild extends Build {

  val appName = "email-extractor"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "org.webjars" %% "webjars-play" % "2.1.0-3",
    "org.webjars" % "jquery" % "1.10.2-1",
    "org.webjars" % "bootstrap" % "3.0.0",
    "org.webjars" % "prettify" % "4-Mar-2013",
    "org.webjars" % "html5shiv" % "3.6.2"
    )

  val main = play.Project(appName, appVersion, appDependencies)
    .settings(Play2WarPlugin.play2WarSettings: _*)
    .settings(
      // Add your own project settings here
      scalaVersion := "2.10.3",
      templatesImport += "models._",
      Play2WarKeys.servletVersion := "3.0"
    )

}
