package com.example.businesscard.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "business_card_table")
data class BusinessCard(
    val cardDesignCode: String,
    val isMy: Boolean,
    val companyName: String,
    val jobTitle: String,
    val ownerName: String,
    val phoneNumber: String,
    val mailAddress: String,
    val websiteUrl: String,
    val address: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}