package com.exame.data.source.remote

import com.exame.data.model.Response
import com.exame.data.model.User
import retrofit2.Call
import retrofit2.http.*

interface ApiRequest {
    @FormUrlEncoded
    @POST("/auth/sign-in")
    fun doLogin(@Field("username") user: String, @Field("password") password: String): Call<User>

    @FormUrlEncoded
    @POST("/auth/sign-up")
    fun doSignup(@Field("username") user: String, @Field("password") password: String, @Field("name") name: String): Call<User>

    @GET("/notifications")
    fun getNotifications(@Header("Authorization") auth: String): Call<Response>
}