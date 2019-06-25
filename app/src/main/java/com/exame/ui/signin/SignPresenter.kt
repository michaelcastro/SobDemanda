package com.exame.ui.signin

import android.annotation.SuppressLint
import com.exame.data.source.DataSource
import com.exame.data.source.remote.RemoteDataSource
import com.exame.utils.Prefs
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignPresenter(val view: SiginContract.View) : SiginContract.Presenter {

    private val dataSource: DataSource = RemoteDataSource()

    @SuppressLint("CheckResult")
    override fun doLogin(username: String, password: String) {

        if (checkForm(username, password)) {
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

    fun checkForm(username: String, password: String): Boolean {
        if (username.trim().isEmpty()) {
            view.showFormError("Username can not be empty")
            return false
        }

        if (password.trim().isEmpty()) {
            view.showFormError("Password can not be empty")
            return false
        }

        return true
    }
}