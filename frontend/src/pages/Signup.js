import { json, useNavigate } from "react-router-dom";
import axios from "axios";

const baseURL = "http://localhost:5000";
// const baseURL = "http://audio-transcribe-services.us-east-2.elasticbeanstalk.com";
const signupURL = baseURL + "/user/register";


const Signup = () => {
    const navigate = useNavigate();
    let errorMessage = '';

    const handleSignup = async (event) => {
        event.preventDefault();
        const username = event.target.username.value;
        const password = event.target.password.value;
        const rePassword = event.target.rePassword.value;
        let requestBody = {
            'username': username,
            'password': password
        }
        console.log(requestBody);
        if(password !== rePassword){
            errorMessage = "Make sure you enter the same password twice!";
        }
        else {
            errorMessage = "";
            const response = await axios.post(
                signupURL, requestBody, {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
                    }
                }
            );
            if (response.status === 200) {
                navigate('/success', {state: {
                    message: "New user was created successfully!"
                }});
            }
            else {
                throw json({ message: 'Could not create the user.' }, { status: 500 });
            }
        }
    }

    return ( <>
        <h1 className="subTitle">Sign up</h1>
        <form action="/user/register" className="form" onSubmit={handleSignup}>
            <div className="field">
                <label htmlFor="username">Username:</label>
                <input type="email" name="username" id="username"></input>
            </div>        
            <div className="field">
                <label htmlFor="password">Password:</label>
                <input type="password" name="password" id="password" ></input>
            </div>
            <div className="field">
                <label htmlFor="rePassword">Re-enter password:</label>
                <input type="password" name="rePassword" id="rePassword" ></input>
            </div>
            <p color="red">{ errorMessage }</p>
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