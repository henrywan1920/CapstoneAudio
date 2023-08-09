const getCredential = () => {
    let username = localStorage.getItem('username');
    if (!username) {
        username = null;
    }

    let password = localStorage.getItem('password');
    if (!password) {
        password = null;
    }

    const credential = {
        username: username,
        password: password
    }
    return credential;
}

export const loader = () => {
    return getCredential();
}

export const checkIsLogin = () => {
    // If user logged in already, we should show 'logout' button.
    // In this case, the tag should not be 'login', so isLogIn should be false
    const username = localStorage.getItem('username');
    const password = localStorage.getItem('password');

    if (!username || !password) {
        return true;
    }
    else {
        return false;
    }
}

export const resetCredential = () => {
    localStorage.setItem('username', null);
    localStorage.setItem('password', null);
}

export default getCredential;