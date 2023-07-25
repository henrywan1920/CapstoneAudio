import React, { useState } from "react";
import { Buffer } from 'buffer';

const Login = () => {
    
    const [userData, setUserData] = useState({
        username: '',
        password: ''
    });
    
    const handleInputChange = (e) => {
        const {name, value} = e.target;
        setUserData({...userData, [name]: value})
    }

    function handleLogin (e) { 
        e.preventDefault();
        const jsonData = JSON.stringify(userData);
        console.log(jsonData);
        
        try {
            let username = 'tom678@gmail.com';
            let password = 'audio123';
            const response = fetch('http://localhost:5000/dummy/user/login', {
                method: 'POST',
                credentials: 'include',
                mode: 'no-cors',
                headers: {
                Authorization: 'Basic ' + Buffer.from(username + ':' + password).toString('base64'),
                'Content-Type': 'application/json'
                },
                body: jsonData,
            });

            if (response.ok) {
                const responseData = response.json();
                //happy path
                console.log(responseData.message);
            }else{
                //sad path
                console.log(response.json());
                throw new Error('Failed to login');
            }
            } catch (error) {
            console.error(error);
            //other error
    }
}

    return ( <>

        <h1 className="subTitle">Log in</h1>
        <form className="form" onSubmit={handleLogin}>
            <div className="field">
                <label htmlFor="username">Username:</label>
                <input type="text" name="username" id="username" value={userData.username}
                onChange={handleInputChange}></input>
            </div>        
            <div className="field">
                <label htmlFor="password">Password:</label>
                <input type="password" name="password" id="password" value={userData.password}
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