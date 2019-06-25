package com.exame.ui.home

class HomeContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun noLogged()
        fun showToast(message : String)
        fun showNotification(message: String)
        fun exit()
    }

    interface Presenter {
        fun getNotification()
        fun signOut()
    }
}