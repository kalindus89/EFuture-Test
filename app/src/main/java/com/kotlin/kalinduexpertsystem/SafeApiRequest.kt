package com.kotlin.kalinduexpertsystem

import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {

        suspend fun <T:Any> apiRequest(call:suspend ()->Response<T>) :T{
            val response=call.invoke()
            if (response.isSuccessful){
                return response.body()!! // !!non nullable
            }else{
                throw ApiExceptions(response.code().toString())
            }
        }
}

class ApiExceptions(msg:String):IOException(msg)