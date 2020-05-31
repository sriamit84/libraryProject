/**
 *
 * This class is responsible to show the list of libraries available in database
 * @author Amit Srivastava
 *
 */

import React, { Component, Fragment } from "react";
import * as actions from "../actions";
import Book from "./Book";
import "./App.css";
class Library extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  // Get all the libraries and store in state
  componentDidMount() {
    let self = this;
    actions.getLibraries().then(libraries => self.setState({ libraries: libraries }));
  }

  // Iterate over all the libraries and show the library details
  render() {
    const libraries = this.state.libraries;

    if (libraries) {
      const listItems = libraries.content.map(item => (
        <li key={item.id}>
          <span className="item-name">
            <a onClick={() => this.onSelect(item.id)}>{item.libraryName}</a>
          </span>
          &nbsp;|&nbsp; {item.description}
          <Book books={item.books} />
          <br />
          <br />
        </li>
      ));
      return <ul className="items">{listItems}</ul>;
    } else {
      return null;
    }
  }

  // on select of library get the list of books for libraryid
  onSelect(id) {
    let libraries = this.state.libraries;
    console.log(libraries);
    let library = libraries.content.filter(lib => lib.id === id);
    if (library && library.length > 0) {
      let lib = library[0];
      lib.books = lib.books || [];
      let self = this;
      let books = lib.books.filter(books => books.content.filter(book => book.content[0].id === id));
      if (!books || books.length == 0) {
        actions.getBooksByLibrary(id).then(book => {
          lib.books.push(book);
          self.setState({ libraries: libraries });
        });
      }
    } else {
      console.log("Error when expanding the books for a library");
    }
  }
}

export default Library;
