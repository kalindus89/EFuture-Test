package com.kotlin.kalinduexpertsystem.models

data class ModelScenarioCaseResponse(
    val id:Int,
    val text:String,
    val image:String,
    val answers:List<ModelAnswers>
    )