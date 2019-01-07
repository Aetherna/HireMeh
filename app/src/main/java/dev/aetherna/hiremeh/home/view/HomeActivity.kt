package dev.aetherna.hiremeh.home.view

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout
import dagger.android.support.DaggerAppCompatActivity
import dev.aetherna.hiremeh.R
import dev.aetherna.hiremeh.common.mvi.MviView
import dev.aetherna.hiremeh.databinding.ActivityHomeBinding
import dev.aetherna.hiremeh.home.HomeViewModel
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
            swipeToRefreshIntent(),
            Observable.just(Initialize)
        )
    }

    override fun render(state: HomeViewState) {
        binding.homeRefresh.isRefreshing = state.isLoading
        binding.viewModel = state
        postsAdapter.submitList(state.posts)
    }

    private fun swipeToRefreshIntent(): Observable<HomeIntent> {
        return RxSwipeRefreshLayout.refreshes(binding.homeRefresh).map { LoadData }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}
