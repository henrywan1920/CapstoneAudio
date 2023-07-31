import React from "react";
import homeImg from "./img/homeImg.png";
const Home = () => {
  return (
    <>
      <container id="hp-container">
        <div class="hp-intro1">
          <p>Audio Translator</p>
          <h2>Breaking Barriers, </h2>
          <h2>One Language at a Time</h2>
        </div>

        <div class="hp-intro2">
          <img className="homeImg" src={homeImg} alt="homeImg"></img>
        </div>
      </container>
    </>
  );
};

export default Home;
