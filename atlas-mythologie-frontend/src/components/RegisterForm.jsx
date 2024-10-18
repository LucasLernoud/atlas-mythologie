import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { registerAndLoginUser } from '../store/UserSlice';
import { useNavigate } from 'react-router-dom';

export const RegisterForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleRegisterSubmit = async (e) => {
        e.preventDefault();
        const userData = { username, password };
        
        // Inscription puis connexion automatique
        const result = await dispatch(registerAndLoginUser(userData));

        // Si tout est OK, rediriger vers une autre page (ex: la page d'accueil)
        if (result.payload) {
            navigate('/');
        }
    };

    return (
        <form onSubmit={handleRegisterSubmit}>
            <label>Nom d'utilisateur</label>
            <input 
                type="text" 
                value={username}
                onChange={(e) => setUsername(e.target.value)} 
                required
            />
            <br />
            <label>Mot de passe</label>
            <input 
                type="password" 
                value={password}
                onChange={(e) => setPassword(e.target.value)} 
                required
            />
            <br />
            <button type="submit">S'inscrire</button>
        </form>
    );
};
