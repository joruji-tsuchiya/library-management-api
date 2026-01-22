package com.github.joruji_tsuchiya.library_management_api.domain

import com.github.joruji_tsuchiya.library_management_api.domain.BookStatus

data class Book(
    val title: String,
    val price: Long,
    val status: BookStatus
) {
    init {
        // タイトルは必須
        require(title.isNotBlank()) { "タイトルは必須です" }

        // 価格は0以上
        require(price >= 0) { "価格は0以上である必要があります" }
    }
}