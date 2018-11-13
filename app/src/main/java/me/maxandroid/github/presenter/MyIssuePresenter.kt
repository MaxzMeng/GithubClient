package me.maxandroid.github.presenter


import me.maxandroid.github.view.fragments.subfragments.MyIssueListFragment
import me.maxandroid.github.model.issue.MyIssuePage
import me.maxandroid.github.network.entities.Issue
import me.maxandroid.github.view.common.CommonListPresenter


class MyIssuePresenter : CommonListPresenter<Issue, MyIssueListFragment>() {
    override val listPage = MyIssuePage()
}