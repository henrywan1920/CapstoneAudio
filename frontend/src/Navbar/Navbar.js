import React from "react";
import {
  Nav,
  NavLink,
  Bars,
  NavMenu,
  NavBtn,
  NavBtnLink,
} from "./NavbarElements";

const Navbar = (props) => {
  const isLogin = props.mode === "login";
  return (
    <>
      <Nav>
        <Bars />

        <NavMenu>
          <NavLink to="/" activeStyle>
            Home
          </NavLink>
          <NavLink to="/audio" activeStyle>
            Dashboard
          </NavLink>
          <NavLink to="/upload" activeStyle>
            Upload
          </NavLink>
          <NavLink to="/signup" activeStyle>
            Register
          </NavLink>
          <NavLink to="/contact" activeStyle>
            Contact Us
          </NavLink>
        </NavMenu>
        <NavBtn>
          <NavBtnLink to={`${isLogin ? 'login' : 'logout'}`}>{isLogin ? 'Login' : 'Logout'}</NavBtnLink>
        </NavBtn>
      </Nav>
    </>
  );
};

export default Navbar;
