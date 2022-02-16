package com.kotlin.kalinduexpertsystem.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlin.kalinduexpertsystem.repository.SystemRepository

class SystemViewModelFactory(private val repository: SystemRepository):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SystemViewModel(repository) as T
    }
}