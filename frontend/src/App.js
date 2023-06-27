import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./pages/Layout";
import Home from "./pages/Home";
import Blogs from "./pages/Blogs";
import Contact from "./pages/Contact";
import NoPage from "./pages/NoPage";
import Login from "./pages/Login";
import Audio from "./pages/Audio";
import Upload from "./pages/Upload";
import Signup from "./pages/Signup";
import Navbar from "./Navbar/Navbar";
import Footer from "./Footer/Footer";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route path="navbar" element={<Navbar />} />
          <Route index element={<Home />} />
          <Route path="blogs" element={<Blogs />} />
          <Route path="contact" element={<Contact />} />
          <Route path="myaudio" element={<Audio />} />
          <Route path="upload" element={<Upload />} />

          <Route path="contact" element={<Contact />} />
          <Route path="signup" element={<Signup />} />

          <Route path="login" element={<Login />} />

          <Route path="*" element={<NoPage />} />
          <Route path="footer" element={<Footer />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
