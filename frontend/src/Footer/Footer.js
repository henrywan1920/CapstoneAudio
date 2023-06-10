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
            <FooterLink href="/contact">ContactUs</FooterLink>
          </Column>
          <Column>
            <FooterLink href="/signup">Register</FooterLink>
          </Column>
          <Column>
            <FooterLink href="/login">Login</FooterLink>
          </Column>
        </Row>
        <h5
          style={{ color: "#F5EFE7", textAlign: "center", margin: "5px 0px" }}
        >
          &copy; 2023 AUDIO.IO BY HUI WAN, STELLA LI & JINGXU LAN
        </h5>
      </Container>
    </Box>
  );
};
export default Footer;
