import React, { useState } from "react";
import { Buffer } from 'buffer';

const baseURL = "http://localhost:5000";
const loginURL = baseURL + "/user/login";

const Login = () => {
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
                Authorization: 'Basic ' + Buffer.from(loginForm.username + ':' + loginForm.password).toString('base64')
            },
            body: null,
        })
        .then((response) => {
            const responseData = response.json();
            console.log(responseData.message);
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