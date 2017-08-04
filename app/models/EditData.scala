package models

import com.mongodb.casbah.Imports._


case class FindData (itemName: String)

case class EditData (itemType: String, itemName: String, description: String, maker: String, warrantyTime: String, price: Double, discount: Double, seller: String)


object FindData {
  /**
    * Convert the item object into a BSON format that MongoDb can store.
    */

  def findDocument(item: FindData): MongoDBObject = {
    val builder = MongoDBObject.newBuilder
    builder += "Name" -> item.itemName
    builder.result
  }
}


object EditData {

   def editDocument(item: EditData): MongoDBObject = {
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
