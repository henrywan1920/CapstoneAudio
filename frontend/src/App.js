import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./pages/Layout";
import Home from "./pages/Home";
import Contact from "./pages/Contact";
import Upload from "./pages/Upload";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Navbar from "./Navbar/NavbarBefore";
import Footer from "./Footer/Footer";
import React from "react";
import Player1 from "./pages/Player";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route path="navbar" element={<Navbar />} />
          <Route index element={<Home />} />
          <Route path="contact" element={<Contact />} />
          <Route path="signup" element={<Signup />} />

          <Route path="login" element={<Login />} />
          <Route path="player" element={<Player1 />} />
          <Route path="upload" element={<Upload />} />
          <Route path="footer" element={<Footer />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
