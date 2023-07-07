import { FaBars } from "react-icons/fa";
import { NavLink as Link } from "react-router-dom";
import styled from "styled-components";

export const Nav = styled.nav`
  background: #f6f1f1;
  height: 70px;
  display: flex;
  justify-content: space-between;
  padding: 5px 150px;
  z-index: 99;
`;

export const NavLink = styled(Link)`
  color: #213555;
  display: flex;
  align-items: center;
  text-decoration: none;
  font-size: 20px;
  font-weight: bold;
  padding: 0 15px;
  height: 100%;
  cursor: pointer;
  &.active {
    color: #808080;
  }
`;

export const Bars = styled(FaBars)`
  display: none;
  color: #d8c4b6;
`;

export const NavMenu = styled.div`
  display: flex;
  align-items: center;
  margin-right: -24px;
`;

export const NavBtn = styled.nav`
  display: flex;
  align-items: center;
  margin-right: 24px;
`;

export const NavBtnLink = styled(Link)`
  border-radius: 4px;
  background: #d8c4b6;
  padding: 10px 22px;
  color: #000000;
  outline: none;
  font-size: 20px;
  font-weight: bold;
  border: none;
  cursor: pointer;
  text-decoration: none;
  margin-left: 24px;
  &:hover {
    background: #fff;
    color: #808080;
  }
`;
