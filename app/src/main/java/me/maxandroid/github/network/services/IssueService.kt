package me.maxandroid.github.network.services

import me.maxandroid.github.network.entities.Issue
import me.maxandroid.github.network.retrofit
import retrofit2.adapter.rxjava.GitHubPaging
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface IssueApi {
    @GET("/issues?filter=all&state=all")
    fun listIssuesOfAuthenticatedUser(@Query("page") page: Int = 1, @Query("per_page") per_page: Int = 20): Observable<GitHubPaging<Issue>>

}

object IssueService : IssueApi by retrofit.create(IssueApi::class.java)