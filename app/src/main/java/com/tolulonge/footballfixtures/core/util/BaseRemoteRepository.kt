package com.tolulonge.footballfixtures.core.util

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class BaseRemoteRepository {

    companion object {
        private const val TAG = "BaseRemoteRepository"
        private const val MESSAGE_KEY = "message"
        private const val ERROR_KEY = "error"
        private const val ERRORS_KEY = "errors"
    }


    suspend inline fun <T> safeApiCall(crossinline callFunction: suspend () -> T): Resource<T> {
        return try {
            val myObject = withContext(Dispatchers.IO) { callFunction.invoke() }
            Resource.Success(myObject)
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                e.printStackTrace()
                Log.e("BaseRemoteRepo", "Call error: ${e.localizedMessage}", e.cause)
                when (e) {
                    is HttpException -> {

                        val body = e.response()?.errorBody()
                        Resource.Error(getErrorMessage(body))
                    }
                    is SocketTimeoutException -> Resource.Error("An error has occurred, try again later.")
                    is IOException -> Resource.Error("Check your internet connection and try again.")
                    else -> Resource.Error("Oops, something wrong happened.")
                }
            }
        }
    }

    fun getErrorMessage(responseBody: ResponseBody?): String {
        val errorValue = responseBody!!.string()
        Log.d(TAG, "getErrorMessage: $errorValue")
        return if (errorValue.contains("Unauthorized"))
            errorValue
        else {
            try {
                val jsonObject = JSONObject(errorValue)

                when {
                    jsonObject.has(MESSAGE_KEY) -> jsonObject.getString(MESSAGE_KEY)
                    jsonObject.has(ERROR_KEY) -> jsonObject.getString(ERROR_KEY)
                    jsonObject.has(ERRORS_KEY) -> jsonObject.getString(ERRORS_KEY)
                    else -> "Something wrong happened"
                }
            } catch (e: Exception) {
                "Something wrong happened"
            }
        }
    }
}
