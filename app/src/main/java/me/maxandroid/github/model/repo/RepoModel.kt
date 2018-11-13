package me.maxandroid.github.model.repo

import me.maxandroid.github.model.page.ListPage
import me.maxandroid.github.network.entities.Repository
import me.maxandroid.github.network.entities.User
import me.maxandroid.github.network.services.RepositoryService
import me.maxandroid.github.utils.format
import retrofit2.adapter.rxjava.GitHubPaging
import rx.Observable
import java.util.*

class RepoListPage(val owner: User?): ListPage<Repository>(){
    override fun getData(page: Int): Observable<GitHubPaging<Repository>> {
        return if(owner == null){
            RepositoryService.allRepositories(page, "pushed:<" + Date().format("yyyy-MM-dd")).map { it.paging }
        } else {
            RepositoryService.listRepositoriesOfUser(owner.login, page)
        }
    }

}