package dev.aetherna.hiremeh.common.api.data

import okhttp3.ResponseBody
import retrofit2.Response
import java.net.HttpURLConnection

class ApiResult<T>(private val response: Response<T>) {

    val body: T?
        get() = response.body()
    val error: ResponseBody?
        get() = response.errorBody()

    val statusCode: Int
        get() = response.code()

    val errorMessage: String? = response.message()

    val isError: Boolean
        get() = !response.isSuccessful

}