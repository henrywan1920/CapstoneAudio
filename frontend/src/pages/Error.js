import Layout from "./Layout";
import generalErrorImg from "./img/generalErrors.png"

const ErrorPage = () => {
    const message = "Oops, something wrong happened!"
    return (
        <>
            <Layout />
            <main style={{ textAlign: 'center' }}>
                <h1>{ message }</h1>
                <img className="generalErrorsImg" src={generalErrorImg} alt="generalErrorImg"></img>
            </main>
        </>
    );
}

export default ErrorPage;