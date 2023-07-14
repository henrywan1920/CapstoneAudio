const baseURL = "http://localhost:5000";
const signupURL = baseURL + "/user/register";

const Signup = () => {
    return ( <>
        <h1 className="subTitle">Sign up</h1>
        <form action={ signupURL } className="form" method="POST">
            <div className="field">
                <label htmlFor="username">Username:</label>
                <input type="text" name="username" id="username"></input>
            </div>        
            <div className="field">
                <label htmlFor="password">Password:</label>
                <input type="password" name="password" id="password"></input>
            </div>
            <div className="field">
                <label htmlFor="re-password">Re-enter password:</label>
                <input type="re-password" name="re-password" id="re-password"></input>
            </div>
            <div className="submit">
                <div>
                    <input type="checkbox" id="terms" name="terms"></input>
                    <label htmlFor="terms">I agree to the <a href="www.google.com">terms</a></label>
                </div>
                <div>
                    <input id="signupRequest" type="submit" value="Submit"></input>
                </div>
            </div>
        </form>
    </> );
}
 
export default Signup;