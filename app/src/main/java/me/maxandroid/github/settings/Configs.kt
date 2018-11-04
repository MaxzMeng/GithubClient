package me.maxandroid.github.settings

import me.maxandroid.github.AppContext
import me.maxandroid.github.common.log.logger
import me.maxandroid.github.utils.deviceId

object Configs {

    object Account{
        val SCOPES = listOf("user", "repo", "notifications", "gist", "admin:org")
        const val clientId = "9c3dbc220876b62d61d9"
        const val clientSecret = "8e1b71aeb18aeaedf35a4d27c5489436e6faa56f"
        const val note = "maxandroid.me"
        const val noteUrl = "http://www.maxandroid.me"

        val fingerPrint by lazy {
            (AppContext.deviceId + clientId).also { logger.info("fingerPrint: "+it) }
        }
    }

}