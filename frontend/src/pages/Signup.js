import { useState } from "react";

const Signup = () => {

    const url = "";

    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [re_password, setRe_password] = useState('');
    const [showText, setShowText] =useState(false);


    function handleSignup (e) { 
        e.preventDefault();
        const userInfo = { username, email, password };

        // fetch('http://localhost:8000/user/register', {
        //     method: 'POST',
        //     headers: {},
        //     body: JSON.stringify(userInfo)
        // }).then(() => {
        //     console.log('new userInfo added');
        //     console.log(userInfo);
        // })
        if(password != re_password && password != ""){
            setShowText(true);
        }else{
            setShowText(false)
        }
        console.log(userInfo);
    }

    return ( <>
        <h1 className="subTitle">Sign up</h1>
        <form className="form" onSubmit={handleSignup}>
            <div className="field">
                <label htmlFor="username">Username:</label>
                <input 
                type="text" name="username" id="username" value={username}
                onChange={(e) => setUsername(e.target.value)}></input>
            </div>
            <div className="field">
                <label htmlFor="email">Email:</label>
                <input 
                type="email" name="email" id="email" value={email}
                onChange={(e) => setEmail(e.target.value)}></input>
            </div>         
            <div className="field">
                <label htmlFor="password">Password:</label>
                <input type="password" name="password" id="password" value={password}
                onChange={(e) => setPassword(e.target.value)}></input>
            </div>
            <div className="field">
                <label htmlFor="re_password">Re-enter password:</label>
                <input type="password" name="re_password" id="re_password" value={re_password}
                onChange={(e) => setRe_password(e.target.value)}></input>
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