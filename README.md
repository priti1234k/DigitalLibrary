# DigitalLibrary
Backend project for a Digital Library Book Management System to store book details.

#Api Details
    1. Create a Book (POST /books)
        Endpoint: /books
        Description: Adds a new book to the catalog.
        Request Body:json
          {
            "title": "The Alchemist",
            "author": "Paulo Coelho",
            "genre": "Fiction",
            "availabilityStatus": "Available"
          }
        Response:
          201 Created on success
          400 Bad Request if validation fails

          
    2. View All Books (GET /books)
      	Endpoint: /books?pageNo=5&pageSize=2
      	Description: Retrieves all books from the catalog.
      	Response:json
         ```{"booksDetailsList":[{"title":"The Alchemist","author":"Paulo","genre":"Fiction","availabilityStatus":"CheckedOut"},{"title":"The Alchemist","author":"Paulo Coelho","genre":"Fiction","availabilityStatus":"Available"}],"pageNo":5,"size":2,"totalNoOfPage":18}```
        Response Code: 200 OK
        
    3. Search Book by ID or Title (GET /books/search)
        Endpoint: /books/search?bookId=1 or /books/search?title=The Alchemist
        Description: Searches for a book by BookID or Title.
        Response:json
        {
          "bookId": 1,
          "title": "The Alchemist",
          "author": "Paulo Coelho",
          "genre": "Fiction",
          "availabilityStatus": "Available"
        }
        Response Code: 200 OK or 404 Not Found

    4. Update Book Details (PUT /books/{bookId})
        Endpoint: /books/1
        Description: Updates book details (title, author, genre, availability).
        Request Body:json
        {
          "title": "The Alchemist - Updated",
          "author": "Paulo Coelho",
          "genre": "Philosophical Fiction",
          "availabilityStatus": "Checked Out"
        }
        Response Code:
        200 OK on success
        404 Not Found if book does not exist
  
  
  5. Delete a Book (DELETE /books/{bookId})
        Endpoint: /books/1
        Description: Deletes a book from the catalog.
        Response Code:
        200 OK on success
        404 Not Found if book does not exist
  
  
  6. Exit System
     Endpoint: /systems/exit
       Response Code:
        200 OK on success


#Additional Considerations
      Validations
      Book ID should be unique.
      Title and Author should be non-empty.
      AvailabilityStatus should be "Available" or "Checked Out".
      Error Handling:
      Custom error messages for 400, 404, 500 responses.
      Pagination for Large Data:
      Use GET /books?page=1&size=10 for better performance.
      

