package com.github.joruji_tsuchiya.library_management_api.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BookTest {

    @Test
    fun `有効な値であれば書籍を作成できる`() {
        Book(
            title = "Kotlin入門",
            price = 3000,
            status = BookStatus.UNPUBLISHED
        )
    }

    @Test
    fun `タイトルが空の場合は作成できない`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Book(title = " ", price = 1000, status = BookStatus.UNPUBLISHED)
        }
    }

    @Test
    fun `価格がマイナスの場合は作成できない`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Book(title = "テスト本", price = -1, status = BookStatus.UNPUBLISHED)
        }
    }
}