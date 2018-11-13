package me.maxandroid.github.model.people

import me.maxandroid.github.network.services.UserService
import me.maxandroid.github.model.page.ListPage
import me.maxandroid.github.network.entities.User
import retrofit2.adapter.rxjava.GitHubPaging
import rx.Observable


class PeoplePageParams(val type: String, val login: String?)

class PeoplePage(val params: PeoplePageParams) : ListPage<User>() {

    enum class Type {
        FOLLOWER, FOLLOWING, ALL
    }

    override fun getData(page: Int): Observable<GitHubPaging<User>> {
        return when (Type.valueOf(params.type)) {
            Type.FOLLOWER -> UserService.followers(params.login!!, page = page)
            Type.FOLLOWING -> UserService.following(params.login!!, page = page)
            Type.ALL -> UserService.allUsers(data.since)
        }
    }

}
