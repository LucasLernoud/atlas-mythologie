import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { registerAndLoginUser } from '../store/UserSlice';

export const RegisterForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [passwordError, setPasswordError] = useState('');

    const { loading, error } = useSelector((state) => state.user);
    const dispatch = useDispatch();

    const handleRegisterSubmit = (e) => {
        e.preventDefault();
        
        // Vérification que les deux mots de passe correspondent
        if (password !== confirmPassword) {
            setPasswordError('Les mots de passe ne sont pas identiques.');
            return;
        }

        setPasswordError('');  // Réinitialiser l'erreur si les mots de passe correspondent
        const userData = { username, password, confirmPassword };
        
        dispatch(registerAndLoginUser(userData));
    }

    return (
        <form className="RegisterForm" onSubmit={handleRegisterSubmit}>
            <label>Pseudo</label>
            <input type="text" required className="form-field" value={username} onChange={(e) => setUsername(e.target.value)} />
            
            <br />
            <label>Mot de passe</label>
            <input type="password" required className="form-field" value={password} onChange={(e) => setPassword(e.target.value)} />
            
            <br />
            <label>Confirmez le mot de passe</label>
            <input type="password" required className="form-field" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} />
            
            {passwordError && <div role="alert" style={{ color: 'red' }}>{passwordError}</div>}
            
            <br />
            <button type="submit" className="form-btn">{loading ? 'Chargement...' : 'S\'inscrire'}</button>
            {error && <div role="alert" style={{ color: 'red' }}>{error}</div>}
        </form>
    );
}
