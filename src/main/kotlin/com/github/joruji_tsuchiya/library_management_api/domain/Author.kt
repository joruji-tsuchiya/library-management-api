package com.github.joruji_tsuchiya.library_management_api.domain

import java.time.LocalDate

data class Author(
    val name: String,
    val birthDate: LocalDate
) {
    init {
        // 名前は必須（空文字不可）
        require(name.isNotBlank()) { "著者名は必須です" }

        // 生年月日は現在日以前であること
        require(!birthDate.isAfter(LocalDate.now())) { "生年月日は未来の日付にできません" }
    }
}