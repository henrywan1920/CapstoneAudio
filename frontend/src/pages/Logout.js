import { redirect } from 'react-router-dom';
import Home from './Home';

const Logout = () => {
  return (
    <div>
      <Home />
    </div>
  );
}

export default Logout;

export function loader() {
  localStorage.removeItem('username');
  localStorage.removeItem('password');
  return redirect('/');
}