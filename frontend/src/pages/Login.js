import React from "react";

const Login = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    
    function handleLogin (e) { 
        e.preventDefault();
        const userInfo = { username, password };

        // fetch('http://localhost:8000/user/register', {
        //     method: 'POST',
        //     headers: {},
        //     body: JSON.stringify(userInfo)
        // }).then(() => {
        //     console.log('new userInfo added');
        //     console.log(userInfo);
        // })
        
        console.log(userInfo);
    }

    return ( <>

        <h1 className="subTitle">Log in</h1>
        <form className="form" onSubmit={handleLogin}>
            <div className="field">
                <label htmlFor="username">Username:</label>
                <input type="text" name="username" id="username" value={username}
                onChange={(e) => setUsername(e.target.value)}></input>
            </div>        
            <div className="field">
                <label htmlFor="password">Password:</label>
                <input type="password" name="password" id="password" value={password}
                onChange={(e) => setPassword(e.target.value)}></input>
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