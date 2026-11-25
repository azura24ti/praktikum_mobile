package com.example.azura_tic.Catatan

import com.google.gson.annotations.SerializedName

data class CatatanModel(
    val id: Long,
    val title: String,
    val content: String,
    @SerializedName("create_at")
    val createAt: String? = null
)