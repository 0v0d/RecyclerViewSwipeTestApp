package com.example.recyclerviewswipetestapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fruit(
    val name: String,
    val description: String,
): Parcelable
