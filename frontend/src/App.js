import { RouterProvider, createBrowserRouter } from "react-router-dom";
import Layout from "./pages/Layout";
import Home from "./pages/Home";
import Contact from "./pages/Contact";
import Upload from "./pages/Upload";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Audio from "./pages/Audio";
import React from "react";

const router = createBrowserRouter([
  {
    path: '/',
    element: <Layout />,
    children: [
      {index: true, element: <Home />},
      {
        path: 'login',
        element: <Login />
      },
      {
        path: 'signup',
        element: <Signup />
      },
      {
        path: 'audio',
        element: <Audio />
      },
      {
        path: 'contact',
        element: <Contact />
      },
      {
        path: 'upload',
        element: <Upload />
      }
    ]
  }
]);

function App() {
  return <RouterProvider router={router} />
}

export default App;
