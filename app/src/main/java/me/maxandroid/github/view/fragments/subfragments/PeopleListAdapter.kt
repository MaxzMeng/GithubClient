package me.maxandroid.github.view.fragments.subfragments

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import kotlinx.android.synthetic.main.item_user.view.*
import me.maxandroid.github.R
import me.maxandroid.github.network.entities.User
import me.maxandroid.github.utils.loadWithGlide
import me.maxandroid.github.view.common.CommonListAdapter

class PeopleListAdapter : CommonListAdapter<User>(R.layout.item_user) {
    override fun onItemClicked(itemView: View, item: User) {
        // todo
    }

    override fun onBindData(viewHolder: ViewHolder, user: User) {
        viewHolder.itemView.apply {
            avatarView.loadWithGlide(user.avatar_url, user.login.first())
            nameView.text = user.login
        }
    }
}