package me.maxandroid.github

import android.os.Bundle
import me.maxandroid.github.mvp.impl.BaseActivity
import me.maxandroid.github.presenter.LoginPresenter

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivity<LoginPresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
