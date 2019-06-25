package com.exame.ui.signup

import android.annotation.SuppressLint
import com.exame.data.source.DataSource
import com.exame.data.source.remote.RemoteDataSource
import com.exame.utils.Prefs
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignupPresenter(val view: SignupContract.View) : SignupContract.Presenter {

    private val dataSource: DataSource = RemoteDataSource()


    @SuppressLint("CheckResult")

    override fun doSignup(fullname : String, username: String, password: String, confirmPassword: String) {
        if (checkForm(fullname, username, password, confirmPassword)) {
            view.showProgress()

            Observable.fromCallable {
                dataSource.doLogin(username, password)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Prefs.saveAccessToken(it!!.accessToken)
                    view.receiveUser(it)
                },
                    {
                        view.hideProgress()
                        view.showToast(it.message!!)
                    }
                )
        }
    }

    fun checkForm(fullname : String, username: String, password: String, confirmPassword: String): Boolean {
        if (fullname.trim().isEmpty()) {
            view.showFormError("Full name can not be empty")
            return false
        }

        if (username.trim().isEmpty()) {
            view.showFormError("Username can not be empty")
            return false
        }

        if (password.trim().isEmpty()) {
            view.showFormError("Password can not be empty")
            return false
        }

        if (confirmPassword.trim().isEmpty()) {
            view.showFormError("Confirm password can not be empty")
            return false
        }

        if (!password.trim().equals(confirmPassword.trim())) {
            view.showFormError("The passwords must match")
            return false
        }

        return true
    }
}