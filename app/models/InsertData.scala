package models

import com.mongodb.casbah.Imports._

case class InsertData (itemType: String, itemName: String, description: String, maker: String, warrantyTime: String, price: Double, discount: Double, seller: String )

object InsertData {

  /**
    * Convert the item object into a BSON format that MongoDb can store.
    */

  def buildMongoDbObject(item: InsertData): MongoDBObject = {
    val builder = MongoDBObject.newBuilder
    builder += "Type" -> item.itemType
    builder += "Name" -> item.itemName
    builder += "Description" -> item.description
    builder += "Maker" -> item.maker
    builder += "WarrantyTime" -> item.warrantyTime
    builder += "Price" -> item.price
    builder += "Discount" -> item.discount
    builder += "Seller" -> item.seller
    builder.result
  }



}
