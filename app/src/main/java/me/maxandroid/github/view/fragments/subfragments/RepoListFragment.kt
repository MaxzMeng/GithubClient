package me.maxandroid.github.view.fragments.subfragments

import me.maxandroid.github.presenter.RepoListPresenter
import com.bennyhuo.tieguanyin.annotations.FragmentBuilder
import com.bennyhuo.tieguanyin.annotations.Optional
import me.maxandroid.github.network.entities.Repository
import me.maxandroid.github.network.entities.User
import me.maxandroid.github.view.common.CommonListFragment

@FragmentBuilder
class RepoListFragment : CommonListFragment<Repository, RepoListPresenter>() {

    @Optional
    var user: User? = null

    override val adapter = RepoListAdapter()
}