package com.example.gitapi.retrofit

import android.util.Log
import com.example.gitapi.model.BaseRequest
import com.google.gson.Gson
import retrofit2.HttpException

class CallbackWrapper(var throwable:Throwable) {

    var message:String? = null

    fun onFailure():String{
        Log.e("","")

        if(throwable is HttpException) {
            val response = throwable as HttpException
            message = parseMessage()

            if (message == null) {
                when (response.code()) {
                    401 -> message = "Não autorizado."
                    404 -> message = "Não encontrado."
                    500 -> message = "Problema interno."
                    429 -> message = "Não autorizado."
                    else -> message = ""
                }
            }
        }else{
            message = throwable.message
        }

         message?.let { return it } ?: return " "
    }

    private fun parseMessage():String?{
        val error = throwable as HttpException
        val errorBody = error.response().errorBody()?.string()
        var errorMessage:String? = null
        try{
            errorMessage = Gson().fromJson<BaseRequest>(errorBody, BaseRequest::class.java).message
        }finally {
            return errorMessage
        }

    }

}