package com.kotlin.kalinduexpertsystem

import com.kotlin.kalinduexpertsystem.models.ModelScenarioCaseResponse
import com.kotlin.kalinduexpertsystem.models.ModelScenariosResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ExpertSystemApi {

    /*
    Suspend function is a function that could be started, paused, and resume.
    One of the most important points to remember about the suspend functions is
    that they are only allowed to be called from a coroutine or another suspend function.*/


    @GET("scenarios/")
    suspend fun getAllScenarios():Response<List<ModelScenariosResponse>>


    @GET("scenarios/cases/{case_id}") // must come id number
    suspend fun getCasesForScenario(@Path("case_id") case_id: Int):Response<List<ModelScenarioCaseResponse>>

    companion object{
        operator fun invoke():ExpertSystemApi{

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://mobileapi.efserver.net/mobile_api_test/")
               // .baseUrl("http://mobileapi.efserver.net/mobile_api_test/")
                .build()
                .create(ExpertSystemApi::class.java)
        }
    }

}