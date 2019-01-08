package dev.aetherna.hiremeh.details

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import dev.aetherna.hiremeh.R
import dev.aetherna.hiremeh.common.util.shortToast
import dev.aetherna.hiremeh.databinding.ActivityDetailsBinding
import dev.aetherna.hiremeh.details.model.DetailsViewModel
import dev.aetherna.hiremeh.details.view.DetailsIntent
import dev.aetherna.hiremeh.details.view.DetailsViewState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailsActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: DetailsViewModel

    private val postId: String
        get() = intent?.extras?.getString(EXTRA_POST_ID).orEmpty()

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, vmFactory)[DetailsViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
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

    private fun render(state: DetailsViewState) {
        binding.viewModel = state
        state.error?.let { shortToast(it) }
    }

    private fun intents(): Observable<DetailsIntent> {
        return Observable.just(DetailsIntent.Initialize(postId))
    }

    companion object {
        const val EXTRA_POST_ID = "DETAILS_ACTIVITY_EXTRA_POST_ID"
    }
}
