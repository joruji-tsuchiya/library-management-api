package com.github.joruji_tsuchiya.library_management_api.domain

interface AuthorRepository {
    /**
     * 著者を保存します。
     */
    fun save(author: Author)

    /**
     * すべての著者を取得します。
     */
    fun findAll(): List<Author>
}