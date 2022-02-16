package com.kotlin.kalinduexpertsystem

import android.view.View
import com.kotlin.kalinduexpertsystem.models.ModelAnswers
import com.kotlin.kalinduexpertsystem.models.ModelScenariosResponse

interface RecyclerCaseClickListener {

    fun onRecyclerCaseItemClick(view: View, answer: ModelAnswers)

}

