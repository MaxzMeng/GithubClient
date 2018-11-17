package me.maxandroid.github.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import me.maxandroid.github.R
import me.maxandroid.github.network.entities.Repository
import me.maxandroid.github.network.services.ActivityService
import me.maxandroid.github.network.services.RepositoryService
import me.maxandroid.github.utils.*
import com.bennyhuo.tieguanyin.annotations.ActivityBuilder
import com.bennyhuo.tieguanyin.annotations.Required
import kotlinx.android.synthetic.main.activity_repo_details.*
import kotlinx.android.synthetic.main.app_bar_details.*
import me.maxandroid.github.view.common.BaseDetailActivity
import me.maxandroid.github.view.common.BaseDetailSwipeFinishableActivity
import me.maxandroid.github.view.config.Themer
import retrofit2.Response
import rx.Subscriber

@ActivityBuilder
class RepoDetailActivity: BaseDetailSwipeFinishableActivity() {

    @Required
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_details)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initDetails()
        reloadDetails()
    }

    private fun initDetails(){
        avatarView.loadWithGlide(repository.owner.avatar_url, repository.owner.login.first())
        collapsingToolbar.title = repository.name

        descriptionView.markdownText = getString(R.string.repo_description_template,
            repository.owner.login,
            repository.owner.html_url,
            repository.name,
            repository.html_url,
            repository.owner.login,
            repository.owner.html_url,
            githubTimeToDate(repository.created_at).view())

        bodyView.text = repository.description

        detailContainer.alpha = 0f

        stars.checkEvent = { isChecked ->
            if(isChecked){
                ActivityService.unstar(repository.owner.login, repository.name)
                    .map { false }
            } else {
                ActivityService.star(repository.owner.login, repository.name)
                    .map { true }
            }.doOnNext { reloadDetails(true) }
        }

        watches.checkEvent = { isChecked ->
            if(isChecked){
                ActivityService.unwatch(repository.owner.login, repository.name)
                    .map { false }
            } else {
                ActivityService.watch(repository.owner.login, repository.name)
                    .map { true }
            }.doOnNext { reloadDetails(true) }
        }

        ActivityService.isStarred(repository.owner.login, repository.name)
            .onErrorReturn {
                if(it is retrofit2.HttpException){
                    it.response() as Response<Any>
                } else {
                    throw it
                }
            }
            .subscribeIgnoreError {
                stars.isChecked = it.isSuccessful
            }

        ActivityService.isWatched(repository.owner.login, repository.name)
            .subscribeIgnoreError {
                watches.isChecked = it.subscribed
            }
    }

    private fun reloadDetails(forceNetwork: Boolean = false){
        RepositoryService.getRepository(repository.owner.login, repository.name, forceNetwork)
            .subscribe(object: Subscriber<Repository>(){
                override fun onStart() {
                    super.onStart()
                    loadingView.animate().alpha(1f).start()
                }

                override fun onNext(t: Repository) {
                    repository = t

                    owner.content = repository.owner.login
                    stars.content = repository.stargazers_count.toString()
                    watches.content = repository.subscribers_count.toString()
                    forks.content = repository.forks_count.toString()
                    issues.content = repository.open_issues_count.toString()

                    loadingView.animate().alpha(0f).start()
                    detailContainer.animate().alpha(1f).start()
                }

                override fun onCompleted() = Unit

                override fun onError(e: Throwable) {
                    loadingView.animate().alpha(0f).start()
                    e.printStackTrace()
                }

            })
    }

}