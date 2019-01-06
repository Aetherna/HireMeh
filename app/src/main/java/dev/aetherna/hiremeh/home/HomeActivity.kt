package dev.aetherna.hiremeh.home

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import dev.aetherna.hiremeh.R
import dev.aetherna.hiremeh.databinding.ActivityHomeBinding
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, vmFactory)[HomeViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.viewModel = HomeState("Ala ma kota")

    }
}
