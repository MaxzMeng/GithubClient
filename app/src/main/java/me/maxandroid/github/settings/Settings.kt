package me.maxandroid.github.settings

import me.maxandroid.github.AppContext
import me.maxandroid.github.R
import me.maxandroid.github.common.sharedpreferences.Preference
import me.maxandroid.github.model.account.AccountManager
import me.maxandroid.github.utils.pref

object Settings {
    var lastPage: Int
        get() = if(lastPageIdString.isEmpty()) 0 else AppContext.resources.getIdentifier(lastPageIdString, "id", AppContext.packageName)
        set(value) {
            lastPageIdString = AppContext.resources.getResourceEntryName(value)
        }

    val defaultPage
        get() = if(AccountManager.isLoggedIn()) defaultPageForUser else defaultPageForVisitor

    private var defaultPageForUser by pref(R.id.navRepos)

    private var defaultPageForVisitor by pref(R.id.navRepos)

    private var lastPageIdString by pref("")

    var themeMode by pref("DAY")
}