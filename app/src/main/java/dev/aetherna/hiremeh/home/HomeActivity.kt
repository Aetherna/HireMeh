package dev.aetherna.hiremeh.home

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout
import dagger.android.support.DaggerAppCompatActivity
import dev.aetherna.hiremeh.R
import dev.aetherna.hiremeh.common.mvi.MviView
import dev.aetherna.hiremeh.common.util.shortToast
import dev.aetherna.hiremeh.databinding.ActivityHomeBinding
import dev.aetherna.hiremeh.details.DetailsActivity
import dev.aetherna.hiremeh.home.model.HomeViewModel
import dev.aetherna.hiremeh.home.view.*
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity(), MviView<HomeIntent, HomeViewState> {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel

    private val postsAdapter = PostsAdapter()
    private val disposables = CompositeDisposable()

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, vmFactory)[HomeViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.homePosts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = postsAdapter
        }
        handleClicksOnAdapter()
    }

    override fun onStart() {
        super.onStart()
        viewModel
            .states()
            .doOnNext { render(it) }
            .subscribe()
            .let { disposables.add(it) }

        viewModel.processIntents(intents())
    }

    override fun intents(): Observable<HomeIntent> {
        return Observable.merge(
            Observable.just(Initialize),
            swipeToRefreshIntent()
        )
    }

    override fun render(state: HomeViewState) {
        postsAdapter.submitList(state.posts)
        binding.homeRefresh.isRefreshing = state.isLoading

        state.error?.let { shortToast(it) }
    }

    private fun swipeToRefreshIntent(): Observable<HomeIntent> {
        return RxSwipeRefreshLayout.refreshes(binding.homeRefresh).map { LoadData }
    }

    private fun handleClicksOnAdapter() {
        return postsAdapter.itemsClicks
            .doOnNext {
                with(Intent(this, DetailsActivity::class.java)) {
                    putExtra(DetailsActivity.EXTRA_POST_ID, it.id)
                    startActivity(this)
                }
            }
            .subscribe()
            .let { disposables.add(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}
