package me.maxandroid.github.view.fragments.subfragments

import com.bennyhuo.tieguanyin.annotations.FragmentBuilder
import com.bennyhuo.tieguanyin.annotations.Optional
import com.bennyhuo.tieguanyin.annotations.Required
import me.maxandroid.github.network.entities.User
import me.maxandroid.github.presenter.PeopleListPresenter
import me.maxandroid.github.view.common.CommonListFragment

@FragmentBuilder
class PeopleListFragment : CommonListFragment<User, PeopleListPresenter>() {
    @Optional
    lateinit var login: String

    @Required
    lateinit var type: String

    override val adapter = PeopleListAdapter()
}