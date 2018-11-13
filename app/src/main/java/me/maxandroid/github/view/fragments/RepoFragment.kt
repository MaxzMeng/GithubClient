package me.maxandroid.github.view.fragments

import android.os.Bundle
import me.maxandroid.github.model.account.AccountManager
import me.maxandroid.github.view.common.CommonViewPagerFragment
import me.maxandroid.github.view.config.FragmentPage
import me.maxandroid.github.view.fragments.subfragments.RepoListFragment
import me.maxandroid.github.view.fragments.subfragments.RepoListFragmentBuilder

class RepoFragment : CommonViewPagerFragment() {
    override fun getFragmentPagesNotLoggedIn(): List<FragmentPage> {
        return listOf(FragmentPage(RepoListFragment(), "All"))
    }

    override fun getFragmentPagesLoggedIn(): List<FragmentPage> {
        return listOf(
            FragmentPage(RepoListFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(
                        RepoListFragmentBuilder.OPTIONAL_USER,
                        AccountManager.currentUser
                    )
                }
            }, "My"),
            FragmentPage(RepoListFragment(), "All")
        )
    }

}