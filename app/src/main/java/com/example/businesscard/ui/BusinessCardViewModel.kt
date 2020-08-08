package com.example.businesscard.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.businesscard.db.BusinessCard
import com.example.businesscard.db.BusinessCardDatabase
import com.example.businesscard.repository.BusinessCardRepository
import kotlinx.coroutines.launch

class BusinessCardViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val businessCardRepository: BusinessCardRepository
    lateinit var getMyCards: LiveData<List<BusinessCard>>
    lateinit var getYourCards: LiveData<List<BusinessCard>>

    init {
        val businessCardDao = BusinessCardDatabase.invoke(application).getBusinessCardDao()
        businessCardRepository = BusinessCardRepository(businessCardDao)
    }

    fun insert(businessCard: BusinessCard) = viewModelScope.launch {
        businessCardRepository.insert(businessCard)
    }

    fun getMyCards() = businessCardRepository.getMyCards().also {
        this.getMyCards = it
    }

    fun getYourCards() = businessCardRepository.getYourCards().also {
        this.getYourCards = it
    }

    fun delete(businessCard: BusinessCard) = viewModelScope.launch {
        businessCardRepository.delete(businessCard)
    }

}