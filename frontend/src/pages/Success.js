import { useLocation } from "react-router-dom";
import successImg from "./img/success.png"


const Success = () => {
    const { state } = useLocation();
    const { message } = state;
    return (
        <>
            <main style={{ textAlign: 'center' }}>
                <h1>{ message }</h1>
                <img className="successImg" src={successImg} alt="successImg"></img>
            </main>
        </>
    );
}

export default Success;