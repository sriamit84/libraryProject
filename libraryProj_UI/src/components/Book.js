/**
 *
 * This class is responsible to show the list of books available in a library from database
 * @author Amit Srivastava
 *
 */
import React from "react";

/**
 * This function will display the list of books for a library
 * @param books- list of books available for a library
 *
 */
const Book = books => {
  console.log(books.content);

  if (books && books.books && books.books.length > 0) {
    const listItems = books.books.map(item =>
      item.content && item.content.length > 0
        ? item.content.map(book => (
            <div className="divRow">
              <div className="divCell">{book.bookName}</div>
              <div className="divCell">{book.authorName}</div>
              <div className="divCell">{book.content}</div>
            </div>
          ))
        : ""
    );
    return (
      <div className="divTable">
        <div className="headRow">
          <div className="divCell">Book Name</div>
          <div className="divCell">Author Name</div>
          <div className="divCell">Content</div>
        </div>
        {listItems}
      </div>
    );
  } else {
    return null;
  }
};

export default Book;
