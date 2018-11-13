package me.maxandroid.github.view.fragments

import me.maxandroid.github.view.common.CommonViewPagerFragment
import me.maxandroid.github.view.config.FragmentPage
import me.maxandroid.github.view.fragments.subfragments.MyIssueListFragment

class MyIssueFragment : CommonViewPagerFragment() {
    override fun getFragmentPagesNotLoggedIn() = listOf(
        FragmentPage(MyIssueListFragment(), "My")
    )

    override fun getFragmentPagesLoggedIn(): List<FragmentPage> = listOf(
        FragmentPage(MyIssueListFragment(), "My")
    )
}