package com.github.joruji_tsuchiya.library_management_api

import com.github.joruji_tsuchiya.library_management_api.domain.Author
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.time.LocalDate

class AuthorTest {

	@Test
	fun `未来の生年月日の著者は作成できない`() {
		val futureDate = LocalDate.now().plusDays(1)

		// IllegalArgumentExceptionが発生することを期待する
		assertThrows(IllegalArgumentException::class.java) {
			Author(
				name = "テスト著者",
				birthDate = futureDate
			)
		}
	}

	@Test
	fun `現在または過去の生年月日の著者は作成できる`() {
		val today = LocalDate.now()
		val yesterday = LocalDate.now().minusDays(1)

		// 例外が発生しなければ成功
		Author(name = "著者A", birthDate = today)
		Author(name = "著者B", birthDate = yesterday)
	}
}