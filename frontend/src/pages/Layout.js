import React from "react";
import { Outlet } from "react-router-dom";
import Navbar from "../Navbar/NavbarBefore";
import "./index.css";
import Footer from "../Footer/Footer";

const Layout = () => {
  return (
    <>
      <div className="teamName">
        <h1>Aud.io</h1>
      </div>
      <Navbar />
      <Outlet />
      <Footer />
    </>
  );
};

export default Layout;
