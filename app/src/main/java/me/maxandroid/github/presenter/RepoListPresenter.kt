package me.maxandroid.github.presenter

import me.maxandroid.github.model.repo.RepoListPage
import me.maxandroid.github.network.entities.Repository
import me.maxandroid.github.view.common.CommonListPresenter
import me.maxandroid.github.view.fragments.subfragments.RepoListFragment

class RepoListPresenter : CommonListPresenter<Repository, RepoListFragment>() {
    override val listPage by lazy {
        RepoListPage(view.user)
    }

}