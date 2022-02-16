package com.kotlin.kalinduexpertsystem.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.kalinduexpertsystem.Coroutines
import com.kotlin.kalinduexpertsystem.models.ModelScenarioCaseResponse
import com.kotlin.kalinduexpertsystem.models.ModelScenariosResponse
import com.kotlin.kalinduexpertsystem.repository.SystemRepository
import kotlinx.coroutines.Job

class SystemViewModel(private val repository: SystemRepository):ViewModel() {

    private lateinit var work: Job

    //--------------------scenarios Api--------------------------
    private val scenarios= MutableLiveData<List<ModelScenariosResponse>>()

    val scenariosLive : LiveData<List<ModelScenariosResponse>>
        get() = scenarios

    fun getScenarios(){

        work = Coroutines.ioThenMain(
            { repository.getAllScenarios() }, {
                scenarios.value = it
            })

    }

    //--------------------Case Api--------------------------
    private val cases= MutableLiveData<List<ModelScenarioCaseResponse>>()

    val casesLive : LiveData<List<ModelScenarioCaseResponse>>
        get() = cases

    fun getCases(caseID: Int){

        work = Coroutines.ioThenMain(
            { repository.getMainCase(caseID) }, {
                cases.value = it
               // cases.postValue(it)
            })

    }
  /*  fun postCases(){

        work = Coroutines.ioThenMain(
            { repository.getMainCase() }, {
                cases.postValue(it)
            })

    }*/

}