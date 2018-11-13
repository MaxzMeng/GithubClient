package me.maxandroid.github.view.fragments.subfragments

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import kotlinx.android.synthetic.main.item_issue.view.*
import me.maxandroid.github.R
import me.maxandroid.github.network.entities.Issue
import me.maxandroid.github.utils.githubTimeToDate
import me.maxandroid.github.utils.htmlText
import me.maxandroid.github.utils.view
import me.maxandroid.github.view.common.CommonListAdapter
import org.jetbrains.anko.imageResource

open class IssueListAdapter : CommonListAdapter<Issue>(R.layout.item_issue) {
    override fun onItemClicked(itemView: View, issue: Issue) {
        // todo
    }

    override fun onBindData(viewHolder: ViewHolder, issue: Issue) {
        viewHolder.itemView.apply {
            iconView.imageResource = if (issue.state == "open") R.drawable.ic_issue_open else R.drawable.ic_issue_closed
            titleView.text = issue.title
            timeView.text = githubTimeToDate(issue.created_at).view()
            bodyView.htmlText = issue.body_html
            commentCount.text = issue.comments.toString()
        }
    }
}