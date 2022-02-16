package com.kotlin.kalinduexpertsystem.repository

import com.kotlin.kalinduexpertsystem.ExpertSystemApi
import com.kotlin.kalinduexpertsystem.SafeApiRequest


class SystemRepository (private val api: ExpertSystemApi): SafeApiRequest()

{
    suspend fun  getAllScenarios()=apiRequest { api.getAllScenarios() }
    suspend fun  getMainCase( caseID: Int)=apiRequest { api.getCasesForScenario(caseID) }

}