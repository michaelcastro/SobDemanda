package com.exame.ui.signup

import com.exame.data.model.User

class SignupContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showToast(message : String)
        fun receiveUser(user : User)
        fun showFormError(message : String)
    }

    interface Presenter {
        fun doSignup(fullname : String, username: String, password: String, confirmPassword: String)
    }
}