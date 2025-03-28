# Digital Library Management System

## Overview
The **Digital Library Management System (DLBMS)** is a web-based application that allows users to manage a catalog of books. Users can add, search, update, delete, and view books through a set of RESTful APIs.

## Features
- Add a new book to the catalog.
- View all available books with pagination.
- Search books by ID or title.
- Update book details.
- Delete books from the catalog.
- Exit the system gracefully.

## API Endpoints

### 1. Create a Book
**Endpoint:** `POST /books`

**Description:** Adds a new book to the catalog.

**Request Body:**
```json
{
  "title": "The Alchemist",
  "author": "Paulo Coelho",
  "genre": "Fiction",
  "availabilityStatus": "Available"
}
```
**Response Codes:**
- `201 Created` on success
- `400 Bad Request` if validation fails

---

### 2. View All Books
**Endpoint:** `GET /books?pageNo=5&pageSize=2`

**Description:** Retrieves all books from the catalog.

**Response:**
```json
{
  "booksDetailsList": [
    {"title": "The Alchemist", "author": "Paulo", "genre": "Fiction", "availabilityStatus": "CheckedOut"},
    {"title": "The Alchemist", "author": "Paulo Coelho", "genre": "Fiction", "availabilityStatus": "Available"}
  ],
  "pageNo": 5,
  "size": 2,
  "totalNoOfPage": 18
}
```
**Response Code:** `200 OK`

---

### 3. Search Book by ID or Title
**Endpoint:** `GET /books/search?bookId=1` or `GET /books/search?title=The Alchemist`

**Description:** Searches for a book by Book ID or Title.

**Response:**
```json
{
  "bookId": 1,
  "title": "The Alchemist",
  "author": "Paulo Coelho",
  "genre": "Fiction",
  "availabilityStatus": "Available"
}
```
**Response Codes:**
- `200 OK` on success
- `404 Not Found` if book does not exist

---

### 4. Update Book Details
**Endpoint:** `PUT /books/{bookId}`

**Description:** Updates book details (title, author, genre, availability).

**Request Body:**
```json
{
  "title": "The Alchemist - Updated",
  "author": "Paulo Coelho",
  "genre": "Philosophical Fiction",
  "availabilityStatus": "Checked Out"
}
```
**Response Codes:**
- `200 OK` on success
- `404 Not Found` if book does not exist

---

### 5. Delete a Book
**Endpoint:** `DELETE /books/{bookId}`

**Description:** Deletes a book from the catalog.

**Response Codes:**
- `200 OK` on success
- `404 Not Found` if book does not exist

---

### 6. Exit System
**Endpoint:** `GET /systems/exit`

**Response Code:** `200 OK` on success

## Additional Considerations
### Validations
- **Book ID** should be unique.
- **Title and Author** should be non-empty.
- **Availability Status** should be either `Available` or `Checked Out`.

### Error Handling
- Custom error messages for `400`, `404`, and `500` responses.

### Pagination for Large Data
- Use `GET /books?page=1&size=10` for better performance.

## Deployment Process
1. **Install MySQL.**
2. **Run the `dlbms.sql` file** within MySQL to set up the database.
3. **Update the properties file** with the correct database credentials (`username`, `password`, `hostname`, `port`).
4. **Build the Maven project** using the command:
   ```sh
   mvn clean install
   ```
5. **Run the application:**
   ```sh
   cd target
   java -jar dlbms-0.0.1-SNAPSHOT.jar
   ```
6. **Access the application** at `http://localhost:8080/`.

At this URL, you will find an index page where you can create, view, search, update, and delete books from the catalog.

