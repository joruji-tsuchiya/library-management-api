package com.github.joruji_tsuchiya.library_management_api.infra

import com.github.joruji_tsuchiya.library_management_api.domain.Author
import com.github.joruji_tsuchiya.library_management_api.domain.AuthorRepository
import com.github.`joruji-tsuchiya`.library.infra.jooq.tables.Authors
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class JooqAuthorRepository(
    private val dsl: DSLContext
) : AuthorRepository {

    override fun save(author: Author) {
        dsl.insertInto(Authors.AUTHORS)
            .set(Authors.AUTHORS.NAME, author.name)
            .set(Authors.AUTHORS.BIRTH_DATE, author.birthDate)
            .execute()
    }

    override fun findAll(): List<Author> {
        return dsl.selectFrom(Authors.AUTHORS)
            .fetch { record ->
                Author(
                    name = record.name!!,
                    birthDate = record.birthDate!!
                )
            }
    }
}