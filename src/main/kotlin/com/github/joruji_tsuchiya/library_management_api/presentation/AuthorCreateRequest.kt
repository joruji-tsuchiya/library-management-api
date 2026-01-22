package com.github.joruji_tsuchiya.library_management_api.presentation

import java.time.LocalDate

// 外部から受け取るデータ（JSON）の形を定義します
data class AuthorCreateRequest(
    val name: String,
    val birthDate: LocalDate
)