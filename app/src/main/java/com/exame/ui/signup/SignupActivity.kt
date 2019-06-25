package com.exame.ui.signup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast
import com.exame.R
import com.exame.data.model.User
import com.exame.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_signup.*


class SignupActivity : AppCompatActivity(), SignupContract.View {

    lateinit var presenter: SignupContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        presenter = SignupPresenter(this)
    }

    fun actionSignup(view: View) {
        presenter.doSignup(
            fullname.text.toString(),
            username.text.toString(),
            password.text.toString(),
            confirm_password.text.toString()
        )
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
