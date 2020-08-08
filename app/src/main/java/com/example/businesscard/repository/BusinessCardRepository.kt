package com.example.businesscard.repository

import androidx.lifecycle.LiveData
import com.example.businesscard.db.BusinessCard
import com.example.businesscard.db.BusinessCardDao
import com.example.businesscard.db.BusinessCardDatabase

class BusinessCardRepository(
    val businessCardDao: BusinessCardDao
) {
    suspend fun insert(businessCard: BusinessCard) =
        businessCardDao.insert(businessCard)

    fun getMyCards(): LiveData<List<BusinessCard>> =
        businessCardDao.getMyCards()

    fun getYourCards(): LiveData<List<BusinessCard>> =
        businessCardDao.getYourCards()

    suspend fun delete(businessCard: BusinessCard) =
        businessCardDao.delete(businessCard)
}