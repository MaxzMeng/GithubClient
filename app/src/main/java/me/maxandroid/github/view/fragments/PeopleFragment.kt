package me.maxandroid.github.view.fragments

import android.os.Bundle
import me.maxandroid.github.model.account.AccountManager
import me.maxandroid.github.model.people.PeoplePage.Type.*
import me.maxandroid.github.view.common.CommonViewPagerFragment
import me.maxandroid.github.view.config.FragmentPage
import me.maxandroid.github.view.fragments.subfragments.PeopleListFragment
import me.maxandroid.github.view.fragments.subfragments.PeopleListFragmentBuilder


class PeopleFragment : CommonViewPagerFragment() {
    override fun getFragmentPagesNotLoggedIn(): List<FragmentPage> {
        return listOf(FragmentPage(PeopleListFragment().also {
            it.arguments = Bundle().apply {
                putString(PeopleListFragmentBuilder.REQUIRED_TYPE, ALL.name)
            }
        }, "All"))
    }

    override fun getFragmentPagesLoggedIn(): List<FragmentPage>
            =
            listOf(
                    FragmentPage(PeopleListFragment().also {
                        it.arguments = Bundle().apply {
                            putString(PeopleListFragmentBuilder.OPTIONAL_LOGIN, AccountManager.currentUser?.login)
                            putString(PeopleListFragmentBuilder.REQUIRED_TYPE, FOLLOWER.name)
                        }
                    }, "Followers"),
                    FragmentPage(PeopleListFragment().also {
                        it.arguments = Bundle().apply {
                            putString(PeopleListFragmentBuilder.OPTIONAL_LOGIN, AccountManager.currentUser!!.login)
                            putString(PeopleListFragmentBuilder.REQUIRED_TYPE, FOLLOWING.name)
                        }
                    }, "Following"),
                    FragmentPage(PeopleListFragment().also {
                        it.arguments = Bundle().apply {
                            putString(PeopleListFragmentBuilder.REQUIRED_TYPE, ALL.name)
                        }
                    }, "All")
            )

}