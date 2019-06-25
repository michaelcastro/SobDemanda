package com.exame.ui.home

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.exame.R
import com.exame.ui.signin.SigninActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomeContract.View {

    lateinit var presenter: HomeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter = HomePresenter(this)
        presenter.getNotification()
    }

    override fun showProgress() {
        message_from_server.visibility = View.GONE
        loading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        message_from_server.visibility = View.VISIBLE
        loading.visibility = View.GONE
    }

    override fun showNotification(message: String) {
        message_from_server.text = message
    }

    override fun noLogged() {
        startActivity(Intent(this, SigninActivity::class.java))
        finish()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun exit() {
        startActivity(Intent(this, SigninActivity::class.java))
        finish()
    }
    fun actionSignout(view: View){
        val builder = AlertDialog.Builder(this)
        builder
            .setMessage("Exit?")
            .setPositiveButton(android.R.string.yes) { dialog, which ->
                presenter.signOut()
            }
            .setNegativeButton(android.R.string.no) { dialog, which -> }
            .show()
    }
}
