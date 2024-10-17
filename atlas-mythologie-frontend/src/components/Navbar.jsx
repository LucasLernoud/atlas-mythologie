import React, { useState } from 'react'
import { Link } from 'react-router-dom';

const getUser = () => {
  let user = localStorage.getItem('user')
  if(user){
    user = JSON.parse(user);    
  }
  else{
    user = null;
  }
  return user;
}

function Navbar() {

  const [user, setUser] = useState(getUser());

  const handleLogout = () => {
    localStorage.removeItem('user');
    setUser(null);

  }

  return (
    <div>
        <ul>
            <li><Link to="/">Home</Link></li>
            <li><Link to="/register">Register</Link></li>
            

            {user? 
            <>
            Hello {user.username}
            <button onClick={handleLogout}>Logout</button>
            </>
            :
            <>
            <li><Link to="/login">Login</Link></li>
            </>
            }


        </ul>
    </div>
  )
}

export default Navbar