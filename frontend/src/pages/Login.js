import React from "react";

const Login = () => {
    return ( <>

        <h1 className="subTitle">Log in</h1>
        <form className="form">
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
                    <button>Submit</button>
                </div>
            </div>
        </form>
        
    </> );
}
 
export default Login;