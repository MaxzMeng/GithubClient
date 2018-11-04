package me.maxandroid.github.network.services


import me.maxandroid.github.network.entities.User
import me.maxandroid.github.network.retrofit
import retrofit2.http.GET
import rx.Observable

interface UserApi {

    @GET("/user")
    fun getAuthenticatedUser(): Observable<User>

}

object UserService : UserApi by retrofit.create(UserApi::class.java)