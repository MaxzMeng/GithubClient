package me.maxandroid.github.presenter

import me.maxandroid.github.BuildConfig
import me.maxandroid.github.model.account.AccountManager
import me.maxandroid.github.mvp.impl.BasePresenter
import me.maxandroid.github.view.LoginActivity

class LoginPresenter : BasePresenter<LoginActivity>() {
    fun doLogin(name: String, passwd: String) {
        AccountManager.username = name
        AccountManager.passwd = passwd
        view.onLoginStart()
        AccountManager.login()
            .subscribe({
                view.onLoginSuccess()
            }, {
                view.onLoginError(it)
            })
    }

    fun checkUserName(name: String) = true

    fun checkPasswd(passwd: String) = true

    override fun onResume() {
        super.onResume()
        if(BuildConfig.DEBUG){
            view.onDataInit(BuildConfig.testUserName, BuildConfig.testPassword)
        } else {
            view.onDataInit(AccountManager.username, AccountManager.passwd)
        }
    }
}