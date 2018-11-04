package me.maxandroid.github.network.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import me.maxandroid.github.common.anno.PoKo

/**
 * Created by benny on 7/3/17.
 */
@PoKo
@Parcelize
data class User(
    var login: String,
    var avatar_url: String,
    var html_url: String,
    var id: Int,
    var type: String,
    var site_admin: Boolean,
    var name: String?,
    var company: String?,
    var blog: String?,
    var location: String?,
    var email: String?,
    var bio: String?,
    var public_repos: Int,
    var public_gists: Int,
    var followers: Int,
    var following: Int,
    var created_at: String,
    var updated_at: String,
    var private_gists: Int,
    var total_private_repos: Int,
    var owned_private_repos: Int,
    var disk_usage: Int,
    var collaborators: Int
) : Parcelable