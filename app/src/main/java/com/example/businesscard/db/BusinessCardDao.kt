package com.example.businesscard.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BusinessCardDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(businessCard: BusinessCard)

    @Query("SELECT * FROM business_card_table WHERE isMy = 1")
    fun getMyCards(): LiveData<List<BusinessCard>>

    @Query("SELECT * FROM business_card_table WHERE isMy = 0")
    fun getYourCards(): LiveData<List<BusinessCard>>

    @Delete
    suspend fun delete(businessCard: BusinessCard)
}