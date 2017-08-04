package models

import com.mongodb.casbah.Imports._

case class DeleteData (itemName: String)

object DeleteData {

  /**
    * Convert the item object into a BSON format that MongoDb can store.
    */

  def buildMongoDbObject(item: DeleteData): MongoDBObject = {
    val builder = MongoDBObject.newBuilder
    builder += "Name" -> item.itemName
    builder.result
  }



}
