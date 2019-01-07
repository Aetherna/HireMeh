package dev.aetherna.hiremeh.home.view

import android.support.v7.util.DiffUtil
import dev.aetherna.hiremeh.R
import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.common.util.DataBindingAdapter

class PostsAdapter() : DataBindingAdapter<Post>(DiffCallback()) {

    override fun getItemViewType(position: Int) = R.layout.item_post
}

class DiffCallback : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(p0: Post, p1: Post): Boolean {
        return p0.id == p1.id
    }

    override fun areContentsTheSame(p0: Post, p1: Post): Boolean {
        return p0 == p1
    }
}