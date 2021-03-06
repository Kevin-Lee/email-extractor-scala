import sbt._
import Keys._
import play.Project._

import com.typesafe.sbteclipse.core.EclipsePlugin.EclipseKeys

import com.github.play2war.plugin._

object ApplicationBuild extends Build {

  val appName = "email-extractor-scala"
  val appVersion = "0.0.9"
  
  override def settings = super.settings ++ Seq(
    EclipseKeys.withSource in ThisBuild := true,
    conflictWarning := ConflictWarning.disable
  )

  val appDependencies = Seq(
    "org.webjars" %% "webjars-play" % "2.1.0-3",
    "org.webjars" % "jquery" % "1.10.2-1",
    "org.webjars" % "bootstrap" % "3.1.1",
    "org.webjars" % "prettify" % "4-Mar-2013",
    "org.webjars" % "html5shiv" % "3.7.0"
    )

  val main = play.Project(appName, appVersion, appDependencies)
    .settings(Play2WarPlugin.play2WarSettings: _*)
    .settings(
      // Add your own project settings here
      scalaVersion := "2.10.6",
//      coffeescriptOptions := Seq("native", "/usr/local/bin/coffee -p"),
      templatesImport += "models._",
      Play2WarKeys.servletVersion := "3.0"
    )

}
