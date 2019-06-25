package com.exame.ui.home

import android.annotation.SuppressLint
import com.exame.data.source.DataSource
import com.exame.data.source.remote.RemoteDataSource
import com.exame.utils.Prefs
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter(val view: HomeContract.View) : HomeContract.Presenter {
    private val dataSource: DataSource = RemoteDataSource()

    @SuppressLint("CheckResult")
    override fun getNotification() {
        view.showProgress()
        val token = Prefs.getAccessToken()
        if (token == null) {
            view.noLogged()
        } else {
            Observable.fromCallable {
                dataSource.getNotifications(token)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.hideProgress()
                    view.showNotification(it!!.message)
                },
                    {
                        view.hideProgress()
                        view.showToast(it.message!!)
                    }
                )

        }
    }

    @SuppressLint("CheckResult")
    override fun signOut(){
        view.showProgress()
        Observable.fromCallable {
            Prefs.deleteAccessToken()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideProgress()
                if (it){
                    view.exit()
                }else{
                    view.showToast("fail")
                }
            },
                {
                    view.hideProgress()
                    view.showToast(it.message!!)
                }
            )
    }
}