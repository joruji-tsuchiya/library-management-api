# Design Document

## 1. データベース設計 (ER図)

書籍 (`books`) と著者 (`authors`) は多対多の関係（1冊の本を複数人で執筆可能、1人の著者が複数冊執筆可能）であるため、中間テーブル (`book_authors`) を導入します。

```mermaid
erDiagram
    BOOKS ||--|{ BOOK_AUTHORS : "written by"
    AUTHORS ||--|{ BOOK_AUTHORS : "writes"

    BOOKS {
        bigint id PK
        varchar title "タイトル"
        bigint price "価格 (0以上)"
        varchar status "UNPUBLISHED, PUBLISHED"
    }

    AUTHORS {
        bigint id PK
        varchar name "著者名"
        date birth_date "生年月日 (現在日以前)"
    }

    BOOK_AUTHORS {
        bigint book_id PK, FK
        bigint author_id PK, FK
    }
```

## 2. ステータス遷移図 (書籍の出版状況)

書籍の出版状況 (`status`) に関するビジネスルールです。
「出版済み (`PUBLISHED`)」となった書籍を「未出版 (`UNPUBLISHED`)」に戻すことはできません。

```mermaid
stateDiagram-v2
    [*] --> UNPUBLISHED : 新規登録
    UNPUBLISHED --> PUBLISHED : 出版処理 (Update)
    PUBLISHED --> PUBLISHED : 情報更新 (Update)
    PUBLISHED --> UNPUBLISHED : 変更不可 (Error)