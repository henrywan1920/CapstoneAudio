import React from "react";
import {
  Nav,
  NavLink,
  Bars,
  NavMenu,
  NavBtn,
  NavBtnLink,
} from "./NavbarElements";

const Navbar = () => {
  return (
    <>
      <Nav>
        <Bars />

        <NavMenu>
          <NavLink to="/" activeStyle>
            Home
          </NavLink>
          <NavLink to="/myaudio" activeStyle>
            My Audio
          </NavLink>
          <NavLink to="/upload" activeStyle>
            Upload
          </NavLink>
          <NavLink to="/contact" activeStyle>
            About As
          </NavLink>
        </NavMenu>
        <NavBtn>
          <NavBtnLink to="/Signup">Sign Up</NavBtnLink>
        </NavBtn>
        <NavBtn>
          <NavBtnLink to="/Login">Login</NavBtnLink>
        </NavBtn>
      </Nav>
    </>
  );
};

export default Navbar;
