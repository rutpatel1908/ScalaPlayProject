package models

import com.mongodb.casbah.MongoConnection

object MongoDBSetup {

  private val SERVER = "localhost"
  private val PORT   = 27017
  private val DATABASE = "ItemsManagement"
  private val COLLECTION = "Items"
  val connection = MongoConnection(SERVER)
  val collection = connection(DATABASE)(COLLECTION)
  val newConnection = MongoConnection(SERVER)(DATABASE)

}