package dev.aetherna.hiremeh.common.api

import dagger.Module
import dagger.Provides
import dev.aetherna.hiremeh.common.api.domain.CommentResponse
import dev.aetherna.hiremeh.common.api.domain.PostResponse
import dev.aetherna.hiremeh.common.api.domain.UserResponse
import dev.aetherna.hiremeh.common.api.retrofit.PostApi
import dev.aetherna.hiremeh.common.api.retrofit.parser.CommentParser
import dev.aetherna.hiremeh.common.api.retrofit.parser.PostParser
import dev.aetherna.hiremeh.common.api.retrofit.parser.ResponseParser
import dev.aetherna.hiremeh.common.api.retrofit.parser.UserParser
import dev.aetherna.hiremeh.common.api.retrofit.parser.error.ApiErrorParser
import dev.aetherna.hiremeh.common.api.retrofit.parser.error.ErrorParser
import dev.aetherna.hiremeh.common.domain.Comment
import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.common.domain.User
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePostApi(retrofit: Retrofit): PostApi {
        return retrofit.create<PostApi>(PostApi::class.java)
    }

    @Provides
    @Singleton
    fun provideErrorParser(): ErrorParser {
        return ApiErrorParser()
    }

    @Provides
    @Singleton
    fun providePostParser(): ResponseParser<PostResponse, Post> {
        return PostParser()
    }

    @Provides
    @Singleton
    fun provideUserParser(): ResponseParser<UserResponse, User> {
        return UserParser()
    }

    @Provides
    @Singleton
    fun provideCommentParser(): ResponseParser<CommentResponse, Comment> {
        return CommentParser()
    }

    @Provides
    @Singleton
    fun provideApi(
        api: PostApi,
        errorParser: ErrorParser,
        postParser: ResponseParser<PostResponse, Post>,
        userParser: ResponseParser<UserResponse, User>,
        commentParser: ResponseParser<CommentResponse, Comment>
    ): Api {
        return DelegatePostApi(api, errorParser, postParser, userParser, commentParser)
    }

}