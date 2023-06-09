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
          <NavLink to="/Login" activeStyle>
            Upload
          </NavLink>
          <NavLink to="/Blogs" activeStyle>
            About As
          </NavLink>
        </NavMenu>
        <NavBtn>
          <NavBtnLink to="/Signup">Sign Up</NavBtnLink>
        </NavBtn>
      </Nav>
    </>
  );
};

export default Navbar;
