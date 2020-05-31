/**
 * @author Amit Srivastava
 * This file contains all the actions to be called from the react components
 *
 */

import { LIBRARIES_URL } from "./Api";

export const getLibraries = () => {
  return new Promise((resolve, reject) => {
    fetch(LIBRARIES_URL)
      .then(res => res.json())
      .then(data => {
        resolve(data);
      })
      .catch(err => {
        reject(err);
      });
  });
};

export const getBooksByLibrary = libraryId => {
  return new Promise((resolve, reject) => {
    fetch(LIBRARIES_URL + "/" + libraryId + "/books")
      .then(res => res.json())
      .then(data => {
        resolve(data);
      })
      .catch(err => {
        reject(err);
      });
  });
};
