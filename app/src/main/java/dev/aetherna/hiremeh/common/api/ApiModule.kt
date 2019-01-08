package dev.aetherna.hiremeh.common.api

import dagger.Module
import dagger.Provides
import dev.aetherna.hiremeh.common.api.domain.PostResponse
import dev.aetherna.hiremeh.common.api.retrofit.PostApi
import dev.aetherna.hiremeh.common.api.retrofit.parser.PostParser
import dev.aetherna.hiremeh.common.api.retrofit.parser.ResponseParser
import dev.aetherna.hiremeh.common.api.retrofit.parser.error.ApiErrorParser
import dev.aetherna.hiremeh.common.api.retrofit.parser.error.ErrorParser
import dev.aetherna.hiremeh.common.domain.Post
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
    fun provideApi(
        api: PostApi,
        errorParser: ErrorParser,
        postParser: ResponseParser<PostResponse, Post>
    ): Api {
        return DelegatePostApi(api, errorParser, postParser)
    }

}