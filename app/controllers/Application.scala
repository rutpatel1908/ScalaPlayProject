package controllers

import akka.stream.scaladsl.{FileIO, Source}
import akka.util.ByteString
import models._
import play.api._
import play.api.mvc._
import com.mongodb.casbah.Imports._
import play.api.http.HttpEntity

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready.")).withSession( "connected" -> "session connected")
  }

  //def secondPage = Action {
    //Ok(views.html.secondPage("Your new application is ready."))
  //}

  //Redirection
  def redirect = Action{
    Redirect("/name?name=Bob")
  }

  //Dynamic URI
  def parameterPassing(name: String) = Action {
      Ok(views.html.secondPage("Hello " + name))
  }

  //Reverse Routing
  def reverseRouting = Action {
    Redirect(routes.Application.plannedUpdates())
  }

  def plannedUpdates = Action {
    Ok("Planned technical updates are in progress.")
  }


  def formSystem = Action {
    Ok(views.html.formSystem("Your form system is ready."))
  }

  def insertForm = Action {
    Ok(views.html.formInsert("Insert Operation"))
  }

  def editForm = Action {
    Ok(views.html.formEdit("Edit Operation"))
  }

  def deleteForm = Action {
    Ok(views.html.formDelete("Delete Operation"))
  }

  def insertData = Action { implicit request =>
    val data1 = InsertData("Game","Call of Duty MW3", "first person shooting game", "Gaming Studio", "14", 49.99, 2.99, "QA")
    val obj = InsertData.buildMongoDbObject(data1)
    MongoDBSetup.collection.save(obj);
    Ok("Stored data in database")
  }

  def deleteData = Action { implicit request =>

    val data1 = DeleteData("Call of Duty MW3")
    val obj = DeleteData.buildMongoDbObject(data1)
    MongoDBSetup.collection.findAndRemove(obj)
    Ok("Stored data in database")
  }

  def updateData = Action { implicit request =>

    val data1 = FindData("Call of Duty MW3")
    val data2 = EditData("Game2","Call of Duty MW3", "first person shooting game", "Gaming Studio", "14", 49.99, 2.99, "QA")
    val obj = FindData.findDocument(data1)
    val obj2 = EditData.editDocument(data2)

    MongoDBSetup.collection.update(obj,obj2)

    Ok("Stored data in database")
  }

  def streamedWithContentLength = Action {

    val file = new java.io.File("C:/Users/Profile/Downloads/ScalaPlayProject (3)/ScalaPlayProject (2)/ScalaPlayProject/gif/kahootAttack.png")
    val path: java.nio.file.Path = file.toPath
    val source: Source[ByteString, _] = FileIO.fromPath(path)

    val contentLength = Some(file.length())

    Result(
      header = ResponseHeader(200, Map.empty),
      body = HttpEntity.Streamed(source, contentLength, Some("application/png"))
    )
  }



}