package com.exame.ui.signin

import com.exame.data.model.User

class SiginContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showToast(message : String)
        fun receiveUser(user : User)
        fun showFormError(message : String)
    }

    interface Presenter {
        fun doLogin(username: String, password: String)
    }
}