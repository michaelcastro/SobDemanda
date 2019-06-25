package com.exame.utils

import com.exame.ApplicationMain
import com.exame.data.model.User
import com.orhanobut.hawk.Hawk

object Prefs {
    enum class PREF_ID {
        user
    }

    fun saveAccessToken(accessToken: String) {
        Hawk.init(ApplicationMain.instance?.applicationContext).build()
        Hawk.put(PREF_ID.user.toString(), accessToken);
    }

    fun getAccessToken(): String? {
        Hawk.init(ApplicationMain.instance?.applicationContext).build()
        return Hawk.get(PREF_ID.user.toString());
    }

    fun deleteAccessToken() : Boolean{
        Hawk.init(ApplicationMain.instance?.applicationContext).build()
        return Hawk.delete(PREF_ID.user.toString());
    }
}