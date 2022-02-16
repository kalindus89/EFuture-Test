package com.kotlin.kalinduexpertsystem

import android.view.View
import com.kotlin.kalinduexpertsystem.models.ModelScenariosResponse

interface RecyclerScenarioClickListener {

    fun onRecyclerScenarioItemClick(view: View, scenario: ModelScenariosResponse)

}

