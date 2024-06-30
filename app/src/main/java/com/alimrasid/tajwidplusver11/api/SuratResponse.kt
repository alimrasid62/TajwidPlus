package com.alimrasid.tajwidplusver11.api

data class SuratResponse(
    val code: Int,
    val message: String,
    val data: List<Surat>
)
