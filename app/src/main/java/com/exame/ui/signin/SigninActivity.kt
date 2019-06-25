package com.exame.ui.signin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast

import com.exame.R
import com.exame.data.model.User
import com.exame.ui.home.HomeActivity
import com.exame.ui.signup.SignupActivity
import kotlinx.android.synthetic.main.activity_signin.*

/**
 * A login screen that offers login via email/password.
 */
class SigninActivity : AppCompatActivity(), SiginContract.View {

    lateinit var presenter: SiginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        presenter = SignPresenter(this)
    }

    fun actionSignin(view: View) {
        presenter.doLogin(username.text.toString(), password.text.toString())
    }

    fun actionSingup(view: View) {
        startActivity(Intent(this, SignupActivity::class.java))
    }

    fun actionForgotPassword(view: View) {
        Toast.makeText(this, "Not implemented yet!", Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        mPasswordFormView.visibility = View.GONE
        loading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        mPasswordFormView.visibility = View.VISIBLE
        loading.visibility = View.GONE
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun receiveUser(user: User) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    override fun showFormError(message: String) {
        Snackbar.make(currentFocus, message, Snackbar.LENGTH_INDEFINITE).setAction("Close", View.OnClickListener {
        }).show()
    }

}
