package com.heshamfas.walmart_coding.entities

data class ProductResponse(
        val products : List<Product>, //List of Product Objects
        val totalProducts : Int, //Total number of products available
        val pageNumber : Int, //Current page number
        val pageSize : Int, //Number of items per page
        val statusCode : Int//HTTP Status
)
data class Product (
        val productId : String,             /*//Unique Id of the product*/
        val productName : String ,       //Product Name
        val shortDescription : String,   //Short Description of the product
        val longDescription : String? ,   //Long Description of the product
        val price : String        ,      //Product price
        val productImage : String ,      //Image url for the product
        val reviewRating : String  ,     //Average review rating for the product
        val reviewCount : String   ,     //Number of reviews
        val inStock : Boolean           //Returns true if item in stock
)

