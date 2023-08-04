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
          <NavLink to="/Home" activeStyle>
            Home
          </NavLink>
          <NavLink to="/audio" activeStyle>
            Dashboard
          </NavLink>
          <NavLink to="/Upload" activeStyle>
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
          <NavBtnLink to="/logout">Logout</NavBtnLink>
        </NavBtn>
      </Nav>
    </>
  );
};

export default Navbar;
