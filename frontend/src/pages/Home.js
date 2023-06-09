import exampleImg from './img/example.jpg';
const Home = () => {
    return ( <>

        <h1>homepage</h1>
        <img className="exampleImg" src={exampleImg} alt="examplePage"></img>
        <div className="scroll-bar">example</div>

    </>
     );
}
 
export default Home;