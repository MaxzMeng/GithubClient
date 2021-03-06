package me.maxandroid.github.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.bennyhuo.tieguanyin.annotations.ActivityBuilder
import kotlinx.android.synthetic.main.activity_login.*
import me.maxandroid.github.R
import me.maxandroid.github.common.ext.otherwise
import me.maxandroid.github.common.ext.yes
import me.maxandroid.github.mvp.impl.BaseActivity
import me.maxandroid.github.presenter.LoginPresenter
import me.maxandroid.github.utils.hideSoftInput
import me.maxandroid.github.view.config.Themer
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.toast

@ActivityBuilder(flags = [Intent.FLAG_ACTIVITY_NO_HISTORY])
class LoginActivity : BaseActivity<LoginPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Themer.applyProperTheme(this)
        setContentView(R.layout.activity_login)

        signInButton.onClick {
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
            (if (show) 0 else 1).toFloat()).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                loginForm.visibility = if (show) View.GONE else View.VISIBLE
            }
        })

        loginProgress.animate().setDuration(shortAnimTime.toLong()).alpha(
            (if (show) 1 else 0).toFloat()).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                loginProgress.visibility = if (show) View.VISIBLE else View.GONE
            }
        })
    }

    private fun showTips(view: EditText, tips: String){
        view.requestFocus()
        view.error = tips
    }

    fun onLoginStart(){
        showProgress(true)
    }

    fun onLoginError(e: Throwable){
        e.printStackTrace()
        toast("登录失败")
        showProgress(false)
    }

    fun onLoginSuccess(){
        toast("登录成功")
        showProgress(false)
        startMainActivity()
    }

    fun onDataInit(name: String, passwd: String){
        username.setText(name)
        password.setText(passwd)
    }
}
