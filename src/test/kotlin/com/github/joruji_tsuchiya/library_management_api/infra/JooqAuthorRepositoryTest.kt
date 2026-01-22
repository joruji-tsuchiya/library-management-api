package com.github.joruji_tsuchiya.library_management_api.infra

import com.github.joruji_tsuchiya.library_management_api.domain.Author
import com.github.joruji_tsuchiya.library_management_api.domain.AuthorRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@SpringBootTest // Spring Bootを起動して本物のDB設定を読み込む
@Transactional  // テストが終わったらデータをロールバック（元に戻す）する
class JooqAuthorRepositoryTest {

    @Autowired
    lateinit var repository: AuthorRepository

    @Test
    fun `著者を保存して取得できる`() {
        // 1. 準備：テスト用の著者データを作成
        val author = Author(
            name = "夏目漱石",
            birthDate = LocalDate.of(1867, 2, 9)
        )

        // 2. 実行：DBに保存
        repository.save(author)

        // 3. 検証：DBから全件取得して、保存したデータが入っているか確認
        val loadedAuthors = repository.findAll()

        // 直近で保存したものが含まれているはず
        val savedAuthor = loadedAuthors.find { it.name == "夏目漱石" }

        // 期待通りかチェック
        assertEquals("夏目漱石", savedAuthor?.name)
        assertEquals(LocalDate.of(1867, 2, 9), savedAuthor?.birthDate)
    }
}