package dev.aetherna.hiremeh.details

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import dev.aetherna.hiremeh.R
import dev.aetherna.hiremeh.databinding.ActivityHomeBinding
import dev.aetherna.hiremeh.details.model.DetailsViewModel
import javax.inject.Inject

class DetailsActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: DetailsViewModel

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, vmFactory)[DetailsViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    companion object {
        const val EXTRA_POST_ID = "DETAILS_ACTIVITY_EXTRA_POST_ID"
    }
}
