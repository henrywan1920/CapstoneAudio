import { RouterProvider, createBrowserRouter } from "react-router-dom";
import Layout from "./pages/Layout";
import Home from "./pages/Home";
import Contact from "./pages/Contact";
import Upload from "./pages/Upload";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Player from "./pages/Player";
import Audio, { loader as audiosLoader } from "./pages/Audio";
import ErrorPage from "./pages/Error";
import React from "react";
import Success from "./pages/Success";

const router = createBrowserRouter([
  {
    path: '/',
    element: <Layout />,
    errorElement: <ErrorPage />,
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
        element: <Audio />,
        loader: audiosLoader,
        children: [
          {
            path: ':playlist/:mediaFileName',
            id: 'media-player',
            element: <Player />
          }
        ]
      },
      {
        path: 'contact',
        element: <Contact />
      },
      {
        path: 'upload',
        element: <Upload />,
      },
      {
        path: 'success',
        element: <Success />
      }
    ]
  }
]);

function App() {
  return <RouterProvider router={router} />
}

export default App;