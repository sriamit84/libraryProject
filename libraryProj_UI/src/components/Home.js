/**
 * This is a Home component and main entry point
 *
 */

import React from "react";
import Header from "./Header";
import Library from "./Library";
const Home = () => {
  return (
    <div className="container">
      <Header />
      <Library />
    </div>
  );
};

export default Home;
