package me.maxandroid.github.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_login.*
import me.maxandroid.github.R
import me.maxandroid.github.common.ext.otherwise
import me.maxandroid.github.common.ext.yes
import me.maxandroid.github.mvp.impl.BaseActivity
import me.maxandroid.github.presenter.LoginPresenter
import me.maxandroid.github.utils.hideSoftInput
import org.jetbrains.anko.toast

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivity<LoginPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signInButton.setOnClickListener {
            presenter.checkUserName(username.text.toString())
                .yes {
                    presenter.checkPasswd(password.text.toString())
                        .yes {
                            hideSoftInput()
                            presenter.doLogin(username.text.toString(), password.text.toString())
                        }
                        .otherwise {
                            showTips(password, "密码不合法")
                        }
                }
                .otherwise {
                    showTips(username, "用户名不合法")
                }
        }
    }

    private fun showProgress(show: Boolean) {
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)
        loginForm.animate().setDuration(shortAnimTime.toLong()).alpha(
            (if (show) 0 else 1).toFloat()
        ).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                loginForm.visibility = if (show) View.GONE else View.VISIBLE
            }
        })

        loginProgress.animate().setDuration(shortAnimTime.toLong()).alpha(
            (if (show) 1 else 0).toFloat()
        ).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                loginProgress.visibility = if (show) View.VISIBLE else View.GONE
            }
        })
    }

    private fun showTips(view: EditText, tips: String) {
        view.requestFocus()
        view.error = tips
    }

    fun onLoginStart() {
        showProgress(true)
    }

    fun onLoginError(e: Throwable) {
        e.printStackTrace()
        toast("登录失败")
        showProgress(false)
    }

    fun onLoginSuccess() {
        toast("登陆成功")
        showProgress(false)
    }

    fun onDataInit(name: String, passwd: String) {
        username.setText(name)
        password.setText(passwd)
    }

}
