import exampleImg from "./img/example.jpg";
const Home = () => {
  return (
    <>
      <container id="hp-container">
        <div class="hp-intro1">
          <p>Audio Translator</p>
          <h2>
            Designed to help non-native speakers to be a native speaker in 5
            secs
          </h2>
        </div>

        <div class="hp-intro2">
          <img className="exampleImg" src={exampleImg} alt="examplePage"></img>
        </div>
      </container>
    </>
  );
};

export default Home;
