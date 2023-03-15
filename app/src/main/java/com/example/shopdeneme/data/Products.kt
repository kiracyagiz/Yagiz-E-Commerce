package com.example.shopdeneme.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
data class Products(
    val id: Int,
    val title: String,
    val price: Double,
    val stock: Int,
    val description: String,
    val category: String,
    val category_id: Int,
    val image: String,
    val saled: Int,
    val rating: Rating
) : Parcelable