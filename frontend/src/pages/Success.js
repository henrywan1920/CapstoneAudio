import Layout from "./Layout";
import successImg from "./img/success.png"

const Success = () => {
    const message = "Success!"
    return (
        <>
            <Layout />
            <main style={{ textAlign: 'center' }}>
                <h1>{ message }</h1>
                <img className="successImg" src={successImg} alt="successImg"></img>
            </main>
        </>
    );
}

export default Success;