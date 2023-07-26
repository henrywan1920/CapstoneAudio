import { useState } from "react";
// const baseURL = "http://localhost:5000";
const baseURL = "http://audio-transcribe-services.us-east-2.elasticbeanstalk.com";
const signupURL = baseURL + "/user/register";


const Signup = () => {

    const [showText, setShowText] =useState(false);

    const [userData, setUserData] = useState({
        password: '',
        rePassword: ''
    });
    const handleInputChange = (e) => {
        const {name, value} = e.target;
        setUserData({...userData, [name]: value})
    }


    const handleSignup = async () => {

        if(userData.password !== userData.rePassword){
            setShowText(true);
            return;
        }

        const userLoginDataJSON = JSON.stringify(userData);
        console.log(userLoginDataJSON);
    
        fetch(signupURL, {
            method: 'POST',
            credentials: 'include'
        })
        .then((response) => {
            const responseData = response.json();
            console.log(responseData.message);
        })
        .catch((error) => console.log(error));
    }

    return ( <>
        <h1 className="subTitle">Sign up</h1>
        <form action={ signupURL } className="form" onSubmit={handleSignup} method="POST">
            <div className="field">
                <label htmlFor="username">Username:</label>
                <input type="email" name="username" id="username"></input>
            </div>        
            <div className="field">
                <label htmlFor="password">Password:</label>
                <input type="password" name="password" id="password" value={userData.password} onChange={handleInputChange}></input>
            </div>
            <div className="field">
                <label htmlFor="rePassword">Re-enter password:</label>
                <input type="password" name="rePassword" id="rePassword" value={userData.rePassword}
                onChange={handleInputChange}></input>
            </div>
            {showText && <h2 color="red">Make sure you enter the same password twice</h2>}
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