package com.github.joruji_tsuchiya.library_management_api.presentation

import com.github.joruji_tsuchiya.library_management_api.domain.Author
import com.github.joruji_tsuchiya.library_management_api.domain.AuthorRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/authors") // "http://localhost:8080/authors" へのアクセスをここで受ける
class AuthorController(
    private val authorRepository: AuthorRepository
) {

    /**
     * 著者の登録 (POST /authors)
     */
    @PostMapping
    fun create(@RequestBody request: AuthorCreateRequest) {
        // 1. リクエスト(DTO)をドメインモデル(Author)に変換
        //    ※ここでAuthorクラスのバリデーション（名前必須など）が自動的に働きます
        val author = Author(
            name = request.name,
            birthDate = request.birthDate
        )

        // 2. リポジトリを使ってDBに保存
        authorRepository.save(author)
    }

    /**
     * 著者の一覧取得 (GET /authors)
     */
    @GetMapping
    fun list(): List<Author> {
        // DBから全件取得してそのまま返す（本来はResponseDTOに変換するのが丁寧ですが今回は直返しします）
        return authorRepository.findAll()
    }
}