package dev.aetherna.hiremeh.common.api.retrofit.parser.error

import android.accounts.NetworkErrorException
import dev.aetherna.hiremeh.common.api.data.ApiResult
import io.reactivex.Observable
import retrofit2.Response

class ApiErrorParser : ErrorParser {

    override fun <T> parse(response: Response<T>): Observable<ApiResult<T>> {
        val result = ApiResult(response)

        return when {
            result.isError -> Observable.error(NetworkErrorException(result.errorMessage))
            else -> Observable.just(result)
        }
    }
}
