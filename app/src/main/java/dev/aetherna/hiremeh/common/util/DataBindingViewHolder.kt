package dev.aetherna.hiremeh.common.util

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import dev.aetherna.hiremeh.BR

class DataBindingViewHolder<T>(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        binding.setVariable(BR.viewModel, item)
        binding.executePendingBindings()
    }
}