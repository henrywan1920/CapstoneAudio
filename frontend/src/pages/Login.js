import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

// const baseURL = "http://localhost:5000";
const baseURL = "http://audio-transcribe-services.us-east-2.elasticbeanstalk.com";
const loginURL = baseURL + "/user/login";

const Login = () => {
    const navigate = useNavigate();
    const [loginForm, setState] = useState({
        username: '',
        password: ''
    });

    const handleInputChange = (e) => {
        const {name, value} = e.target;
        setState({...loginForm, [name]: value})
    }

    const handleLogin = async () => {
        const userLoginDataJSON = JSON.stringify(loginForm);
        console.log(userLoginDataJSON);
    
        fetch(loginURL, {
            method: 'POST',
            credentials: 'include',
            headers: {
                "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"                
            },
            body: {
                "username": loginForm.username,
                "password": loginForm.password
            }
        })
        .then((response) => {
            const responseData = response.json();
            console.log(responseData.message);
            if (response.ok) {
                navigate("/Upload");
            }
            else {
                navigate("/login");
            }
        })
        .catch((error) => console.log(error));
    }

    return ( <>
        <h1 className="subTitle">Log in</h1>
        <form action={ loginURL } className="form" onSubmit={handleLogin} method="POST">
            <div className="field">
                <label htmlFor="username">Username:</label>
                <input type="text" name="username" id="username" value={loginForm.username}
                onChange={handleInputChange}></input>
            </div>        
            <div className="field">
                <label htmlFor="password">Password:</label>
                <input type="password" name="password" id="password" value={loginForm.password}
                onChange={handleInputChange}></input>
            </div>
            <div className="submit">
                <div>
                    <input type="checkbox" id="terms" name="terms"></input>
                    <label htmlFor="terms">I agree to the <a href="www.google.com">terms</a></label>
                </div>
                <div>
                    <button>Submit</button>
                </div>
            </div>
        </form>
    </> );
}
 
export default Login;