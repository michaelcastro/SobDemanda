package com.exame.data.source

import com.exame.data.model.Response
import com.exame.data.model.User

interface DataSource {
    fun doLogin(user: String, password: String): User?
    fun doSignup(user: String, password: String , name: String): User?
    fun getNotifications(auth: String): Response?
}