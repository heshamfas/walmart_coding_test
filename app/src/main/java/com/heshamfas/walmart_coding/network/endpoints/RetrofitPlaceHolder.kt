package com.heshamfas.walmart_coding.network.endpoints

import com.heshamfas.walmart_coding.entities.ProductResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitPlaceHolder {

    @GET("/walmartproducts/{pageNumber}/{pageSize}")
    fun getProductRx(@Path("pageNumber") pageNumber: Int,@Path("pageSize") pageSize: Int): Single<ProductResponse>


}