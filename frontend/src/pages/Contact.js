import React from "react";
import contactImg from "./img/contactUs.jpg";

const Contact = () => {
  return (
    <>
      <container id="contact-page">
        <div class="contact1">
          <img className="contactImg" src={contactImg} alt="contactImg"></img>
        </div>

        <div class="contact1">
          <h1>Please feel free to reach out!</h1>
          <br />
          <strong>Phone: 226-888-8888</strong>
          <br />
          <br />
          <a
            href="mailto:sli0104@conestogac.on.ca"
            id="contact-email"
            style={{ textDecoration: "none" }}
          >
            <strong>Email: sli0104@conestogac.on.ca</strong>
          </a>
          <br />
          <br />
          <strong>Address: Waterloo Campus, Conestoga College</strong>
        </div>
      </container>
    </>
  );
};

export default Contact;
