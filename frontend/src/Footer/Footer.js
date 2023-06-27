import React from "react";
import {
  Box,
  Container,
  Row,
  Column,
  FooterLink,
  Heading,
} from "./FooterStyle";

const Footer = () => {
  return (
    <Box>
      <Container>
        <Row>
          <Column>
            <FooterLink href="/home">Home</FooterLink>
          </Column>
          <Column>
            <FooterLink href="/blogs">Blogs</FooterLink>
          </Column>
          <Column>
            <FooterLink href="/signin">Signin</FooterLink>
          </Column>
          <Column>
            <FooterLink href="/signup">Signup</FooterLink>
          </Column>
        </Row>
        <h4
          style={{ color: "#F5EFE7", textAlign: "center", marginTop: "20px" }}
        >
          &copy; 2023 AUDIO.IO BY HUI WAN, STELLA LI & JINGXU LAN
        </h4>
      </Container>
    </Box>
  );
};
export default Footer;
