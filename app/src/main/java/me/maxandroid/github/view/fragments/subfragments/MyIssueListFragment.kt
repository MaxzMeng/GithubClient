package me.maxandroid.github.view.fragments.subfragments

import me.maxandroid.github.network.entities.Issue
import me.maxandroid.github.presenter.MyIssuePresenter
import me.maxandroid.github.view.common.CommonListFragment

class MyIssueListFragment : CommonListFragment<Issue, MyIssuePresenter>() {
    companion object {
        const val REPOSITORY_NAME = "repository_name"
        const val OWNER_LOGIN = "owner_login"
    }

    override val adapter = IssueListAdapter()
}