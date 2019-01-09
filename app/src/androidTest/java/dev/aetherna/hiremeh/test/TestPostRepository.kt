package dev.aetherna.hiremeh.test

import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.common.repository.Repository
import dev.aetherna.hiremeh.home.view.PostDetails
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject


enum class TestPostRepository : Repository {

    Instance;

    val allPostsSubject = PublishSubject.create<List<Post>>()
    val postDetails = PublishSubject.create<PostDetails>()

    override fun getAllPosts(): Observable<List<Post>> = allPostsSubject
        .subscribeOn(Schedulers.trampoline())
        .observeOn(AndroidSchedulers.mainThread())

    override fun getPostDetails(postId: String) = postDetails
        .subscribeOn(Schedulers.trampoline())
        .observeOn(AndroidSchedulers.mainThread())
}