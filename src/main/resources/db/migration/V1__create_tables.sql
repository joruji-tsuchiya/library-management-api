-- 著者テーブル
CREATE TABLE authors (
                         id         BIGSERIAL    PRIMARY KEY,
                         name       VARCHAR(255) NOT NULL,
                         birth_date DATE         NOT NULL
);

-- 書籍テーブル
CREATE TABLE books (
                       id     BIGSERIAL    PRIMARY KEY,
                       title  VARCHAR(255) NOT NULL,
                       price  BIGINT       NOT NULL CHECK (price >= 0),
                       status VARCHAR(50)  NOT NULL CHECK (status IN ('UNPUBLISHED', 'PUBLISHED'))
);

-- 書籍・著者 中間テーブル
CREATE TABLE book_authors (
                              book_id   BIGINT NOT NULL,
                              author_id BIGINT NOT NULL,
                              PRIMARY KEY (book_id, author_id),
                              CONSTRAINT fk_book_authors_book FOREIGN KEY (book_id) REFERENCES books (id),
                              CONSTRAINT fk_book_authors_author FOREIGN KEY (author_id) REFERENCES authors (id)
);

CREATE INDEX idx_books_status ON books(status);