package me.maxandroid.github.model.issue

import me.maxandroid.github.model.page.ListPage
import me.maxandroid.github.network.entities.Issue
import me.maxandroid.github.network.services.IssueService
import retrofit2.adapter.rxjava.GitHubPaging
import rx.Observable

class MyIssuePage : ListPage<Issue>() {
    override fun getData(page: Int): Observable<GitHubPaging<Issue>> {
        return IssueService.listIssuesOfAuthenticatedUser(page = page)
    }
}