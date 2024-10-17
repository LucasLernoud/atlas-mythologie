import React, { useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { loginUser } from '../store/UserSlice';
import { useNavigate } from 'react-router-dom';

export const LoginForm = () => {
    
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const {loading, error} = useSelector((state) => state.user);

    const dispatch = useDispatch()
    const navigate = useNavigate()

    const handleLoginSubmit = (e) => {
        e.preventDefault();
        let userCredentials = {
            username,
            password
        }
        dispatch(loginUser(userCredentials)).then((result) => 
        {
            if(result.payload){
                setUsername('');
                setPassword('');
                navigate('/');
            }
        })
    }
    
  return (
    <form className='LoginForm' onSubmit={handleLoginSubmit}>
        <label>Pseudo</label>
        <input type="text" required className='form-field' value={username} onChange= {(e) => setUsername(e.target.value)} />
        <br />
        <label>Mot de passe</label>
        <input type="password" required className='form-field' value={password} onChange={(e) => setPassword(e.target.value)}/>
        <br />
        <button type='submit' className='form-btn'>{loading? 'chargement...':'Se connecter'}</button>
        {error&&(
            <div role='alert'>{error}</div>
        )}
    </form>
  )
}
