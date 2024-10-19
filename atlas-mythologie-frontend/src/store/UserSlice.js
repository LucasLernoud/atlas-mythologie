import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { hostName } from "../config";

// Login de l'utilisateur
export const loginUser = createAsyncThunk(
    'user/loginUser',
    async (userCredentials, { rejectWithValue }) => {
        try {
            const request = await axios.post(`${hostName}api/users/login`, userCredentials);
            const response = request.data;
            localStorage.setItem('user', JSON.stringify(response));
            return response;
        } catch (error) {
            if (error.response && error.response.status === 401) {
                return rejectWithValue('Nom d\'utilisateur ou mot de passe incorrect.');
            }
            return rejectWithValue('Erreur de connexion.');
        }
    }
);

// Inscription et login
export const registerAndLoginUser = createAsyncThunk(
    'user/registerAndLoginUser',
    async (userData, { dispatch, rejectWithValue }) => {
        try {
            const registerResponse = await axios.post(`${hostName}api/users/register`, userData);

            if (registerResponse.data) {
                const loginResponse = await dispatch(loginUser({
                    username: userData.username,
                    password: userData.password
                }));
                return loginResponse.payload;
            }
        } catch (error) {
            if (error.response && error.response.status === 409) {
                return rejectWithValue('Nom d\'utilisateur déjà pris.');
            }
            return rejectWithValue('Erreur lors de l\'inscription. Veuillez réessayer.');
        }
    }
);

// Slice pour l'utilisateur
const userSlice = createSlice({
    name: 'user',
    initialState: {
        loading: false,
        user: null,
        error: null
    },
    extraReducers: (builder) => {
        builder
            .addCase(loginUser.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(loginUser.fulfilled, (state, action) => {
                state.loading = false;
                state.user = action.payload;
                state.error = null;
            })
            .addCase(loginUser.rejected, (state, action) => {
                state.loading = false;
                state.user = null;
                state.error = action.payload; // Récupérer l'erreur de rejectWithValue
            })
            .addCase(registerAndLoginUser.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(registerAndLoginUser.fulfilled, (state, action) => {
                state.loading = false;
                state.user = action.payload;
                state.error = null;
            })
            .addCase(registerAndLoginUser.rejected, (state, action) => {
                state.loading = false;
                state.user = null;
                state.error = action.payload; // Affiche l'erreur ici aussi
            });
    }
});

export default userSlice.reducer;
