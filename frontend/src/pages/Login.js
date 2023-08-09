import { useNavigate, json } from "react-router-dom";
import axios from "axios";

const baseURL = "http://localhost:5000";
// const baseURL = "http://audio-transcribe-services.us-east-2.elasticbeanstalk.com";
const loginURL = baseURL + "/user/login";

const Login = () => {
    const navigate = useNavigate();

    const handleLogin = async (event) => {
        event.preventDefault();
        let requestBody = {
            'username': event.target.username.value,
            'password': event.target.password.value
        }
        const response = await axios.post(
            loginURL, requestBody, {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
                }
            }
        );
        
        if (response.status === 200) {
            localStorage.setItem('username', requestBody.username);
            localStorage.setItem('password', requestBody.password);
            navigate('/');
        }
        else {
            throw json({ message: 'Login failed.' }, { status: 500 });
        }
    }

    return ( <>
        <h1 className="subTitle">Log in</h1>
        <form className="form" onSubmit={handleLogin}>
            <div className="field">
                <label htmlFor="username">Username:</label>
                <input type="text" name="username" id="username"></input>
            </div>        
            <div className="field">
                <label htmlFor="password">Password:</label>
                <input type="password" name="password" id="password"></input>
            </div>
            <div className="submit">
                <div>
                    <input type="checkbox" id="terms" name="terms"></input>
                    <label htmlFor="terms">I agree to the <a href="www.google.com">terms</a></label>
                </div>
                <div>
                    <input id="loginRequest" type="submit" value="Submit"></input>
                </div>
            </div>
        </form>
    </> );
}
 
export default Login;