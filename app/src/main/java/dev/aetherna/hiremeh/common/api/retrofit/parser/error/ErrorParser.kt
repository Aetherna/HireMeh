package dev.aetherna.hiremeh.common.api.retrofit.parser.error

import dev.aetherna.hiremeh.common.api.data.ApiResult
import io.reactivex.Observable
import retrofit2.Response

interface ErrorParser {
    fun <T> parse(response: Response<T>): Observable<ApiResult<T>>
}