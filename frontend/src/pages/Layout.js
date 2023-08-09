import React from "react";
import { Outlet, useRouteLoaderData } from "react-router-dom";
import Navbar from "../Navbar/Navbar";
import "./index.css";
import Footer from "../Footer/Footer";

const Layout = () => {
  const credential = useRouteLoaderData('root');
  console.log(credential);
  const isLogin = (credential.username && credential.password) ? false: true;
  return (
    <>
      <div className="teamName">
        <h1>Aud.io</h1>
      </div>
      <Navbar mode={isLogin ? 'login' : 'logout'}/>
      <Outlet />
      <Footer />
    </>
  );
};

export default Layout;
