package me.maxandroid.github.view.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.maxandroid.github.R
import me.maxandroid.github.utils.attrStyle
import me.maxandroid.github.utils.markdownText
import me.maxandroid.github.view.common.CommonSinglePageFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.AnkoContext.Companion
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.support.v4.nestedScrollView

class AboutFragment : CommonSinglePageFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return AboutFragmentUI().createView(Companion.create(context!!, this))
    }
}

class AboutFragmentUI : AnkoComponent<AboutFragment> {
    override fun createView(ui: AnkoContext<AboutFragment>) = ui.apply {
        nestedScrollView {
            verticalLayout {

                imageView {
                    imageResource = R.mipmap.ic_launcher
                }.lparams(width = wrapContent, height = wrapContent) {
                    gravity = Gravity.CENTER_HORIZONTAL
                }
                themedTextView("GitHub", R.style.detail_title) {

                }.lparams(width = wrapContent, height = wrapContent) {
                    gravity = Gravity.CENTER_HORIZONTAL
                }
                themedTextView("MaxzMeng", R.style.detail_description) {

                }.lparams(width = wrapContent, height = wrapContent) {
                    gravity = Gravity.CENTER_HORIZONTAL
                }
                themedTextView(R.string.open_source_licenses, R.style.detail_description) {

                    onClick {
                        alert {
                            customView {
                                scrollView {
                                    textView {
                                        padding = dip(10)
                                        markdownText = context.assets.open("licenses.md").bufferedReader().readText()
                                    }
                                }
                            }
                        }.show()
                    }
                }.lparams(width = wrapContent, height = wrapContent) {
                    gravity = Gravity.CENTER_HORIZONTAL
                }
            }.lparams(width = wrapContent, height = wrapContent) {
                gravity = Gravity.CENTER
            }
        }
    }.view

}