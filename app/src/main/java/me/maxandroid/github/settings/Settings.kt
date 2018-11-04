package me.maxandroid.github.settings

import me.maxandroid.github.AppContext
import me.maxandroid.github.common.sharedpreferences.Preference

object Settings {
    var email: String by Preference(AppContext, "email", "")
    var password: String by Preference(AppContext, "password", "")
}