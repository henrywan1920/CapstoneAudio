import { useState } from "react";

const Signup = () => {

    const [showText, setShowText] =useState(false);

    const [userData, setUserData] = useState({
        username: '',
        email: '',
        password: '',
        re_password: '',
        role: 'temp',
    });


    const handleInputChange = (e) => {
        const {name, value} = e.target;
        setUserData({...userData, [name]: value})
    }

    function handleSignup (e) { 
        e.preventDefault();

        if(userData.password !== userData.re_password){
            setShowText(true);
            return;
        }

        setShowText(false);
        
        const {re_password, ...userInfo} = userData;

        const jsonData = JSON.stringify(userInfo);
        console.log(jsonData);

        try {
            const response = fetch('/user/register', {
                method: 'POST',
                headers: {
                'Content-Type': 'application/json',
                },
                body: JSON.stringify(jsonData),
            });

            if (response.ok) {
                const responseData = response.json();
                //handle happy path
                console.log(responseData.message);
            }else{
                //sad path
                throw new Error('Failed to sign up');
            }
            } catch (error) {
            console.error(error);
            //other error
    }
        
    }

    return ( <>
        <h1 className="subTitle">Sign up</h1>
        <form className="form" onSubmit={handleSignup}>
            <div className="field">
                <label htmlFor="username">Username:</label>
                <input 
                type="text" name="username" id="username" value={userData.username}
                onChange={handleInputChange}></input>
            </div>
            <div className="field">
                <label htmlFor="email">Email:</label>
                <input 
                type="email" name="email" id="email" value={userData.email}
                onChange={handleInputChange}></input>
            </div>         
            <div className="field">
                <label htmlFor="password">Password:</label>
                <input type="password" name="password" id="password" value={userData.password}
                onChange={handleInputChange}></input>
            </div>
            <div className="field">
                <label htmlFor="re_password">Re-enter password:</label>
                <input type="password" name="re_password" id="re_password" value={userData.re_password}
                onChange={handleInputChange}></input>
            </div>
            {showText && <h2 color="red">Make sure you enter the same password twice</h2>}
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
 
export default Signup;