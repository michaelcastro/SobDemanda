package com.exame.data.source.remote

import com.exame.BuildConfig
import com.exame.data.model.Response
import com.exame.data.model.User
import com.exame.data.source.DataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource : DataSource {


    private var request: ApiRequest

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.URL_BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        request = retrofit.create(ApiRequest::class.java)
    }

    override fun doLogin(user: String, password: String): User? {
        val call = request.doLogin(user, password)
        return call.execute().body()
    }

    override fun doSignup(user: String, password: String, name: String): User? {
        val call = request.doSignup(user, password, name)
        return call.execute().body()
    }

    override fun getNotifications(auth: String): Response? {
        val call = request.getNotifications("Bearer " + auth)
        return call.execute().body()

    }

}