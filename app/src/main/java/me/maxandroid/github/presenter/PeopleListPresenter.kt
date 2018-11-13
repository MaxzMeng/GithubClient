package me.maxandroid.github.presenter

import me.maxandroid.github.view.fragments.subfragments.PeopleListFragment
import me.maxandroid.github.model.page.ListPage
import me.maxandroid.github.model.people.PeoplePage
import me.maxandroid.github.model.people.PeoplePageParams
import me.maxandroid.github.network.entities.User
import me.maxandroid.github.view.common.CommonListPresenter


class PeopleListPresenter : CommonListPresenter<User, PeopleListFragment>(){

    override val listPage: ListPage<User> by lazy {
        PeoplePage(PeoplePageParams(view.type, view.login))
    }

}