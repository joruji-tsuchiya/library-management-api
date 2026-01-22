# API Specification

## 1. 著者管理 (Authors)

### 1.1 著者登録
- **Endpoint**: `POST /api/v1/authors`
- **Description**: 新しい著者を登録します。
- **Request Body**:
  ```json
  {
    "name": "Robert C. Martin",
    "birthDate": "1952-12-05"
  }

* **Validation**:
* `name`: 必須
* `birthDate`: 必須、かつ現在日以前であること



### 1.2 著者更新

* **Endpoint**: `PUT /api/v1/authors/{authorId}`
* **Description**: 既存の著者情報を更新します。
* **Request Body**:
```json
{
  "name": "Uncle Bob",
  "birthDate": "1952-12-05"
}

```


* **Validation**:
* `name`: 必須
* `birthDate`: 必須、かつ現在日以前であること



---

## 2. 書籍管理 (Books)

### 2.1 書籍登録

* **Endpoint**: `POST /api/v1/books`
* **Description**: 新しい書籍を登録します。著者はIDリストで指定します（中間テーブルへ保存）。
* **Request Body**:
```json
{
  "title": "Clean Code",
  "price": 3500,
  "status": "UNPUBLISHED",
  "authorIds": [1, 2]
}

```


* **Validation**:
* `title`: 必須
* `price`: 必須、0以上であること
* `status`: 必須 (`UNPUBLISHED` or `PUBLISHED`)
* `authorIds`: 必須、リストは空でないこと（最低1人の著者）



### 2.2 書籍更新

* **Endpoint**: `PUT /api/v1/books/{bookId}`
* **Description**: 書籍情報を更新します。著者の変更も可能です。
* **Request Body**:
```json
{
  "title": "Clean Code (Revised)",
  "price": 3800,
  "status": "PUBLISHED",
  "authorIds": [1, 3]
}

```


* **Validation (Business Rule)**:
* 基本的なバリデーションは登録時と同様。
* **ステータス遷移制約**: 現在のステータスが `PUBLISHED` の場合、`UNPUBLISHED` への変更リクエストは `400 Bad Request` エラーとする。



### 2.3 著者別書籍一覧取得

* **Endpoint**: `GET /api/v1/authors/{authorId}/books`
* **Description**: 指定した著者が執筆に関わった書籍の一覧を返します。
* **Response Example**:
```json
[
  {
    "id": 10,
    "title": "Clean Code",
    "price": 3500,
    "status": "PUBLISHED",
    "authors": [
      { "id": 1, "name": "Robert C. Martin", "birthDate": "1952-12-05" }
    ]
  }
]

```
