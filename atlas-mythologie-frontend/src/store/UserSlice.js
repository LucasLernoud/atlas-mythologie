import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { hostName } from "../config";

export const loginUser = createAsyncThunk(
    'user/loginUser',
    async(userCredentials) => {
        const request = await axios.post(`${hostName}api/users/login`, userCredentials);
        console.log(request.data)
        console.log(request.data.username);
        
        const response = await request.data;
        localStorage.setItem('user', JSON.stringify(response));
        return response;
    }
)

export const registerAndLoginUser = createAsyncThunk(
    'user/registerAndLoginUser',
    async (userData, { dispatch }) => {
        //inscription
        const registerResponse = await axios.post(`${hostName}api/users/register`, userData);

        //login
        if (registerResponse.data) {
            const loginResponse = await dispatch(loginUser({
                username: userData.username,
                password: userData.password
            }));

            return loginResponse.payload;
        }
    }
);

const userSlice = createSlice({
    name: 'user',
    initialState:{
        loading: false,
        user: null,
        error: null
    },
    extraReducers:(builder)=>{
        builder
            .addCase(loginUser.pending, (state)=>{
                state.loading = true;
                state.user = null;
                state.error = null;
            })
            .addCase(loginUser.fulfilled, (state, action) => {
                state.loading = false;
                state.user = action.payload;
                state.error = null;
            })
            .addCase(loginUser.rejected, (state, action)=> {
                state.loading = false;
                state.user = null;
                console.log(action.error.message);
                if(action.error.message === 'Request failed with status code 401'){
                    state.error = 'Nom d\'utilisateur ou mot de passe incorrect'
                }
                else{
                    state.error = action.error.message;
                }
            })

    }
});

export default userSlice.reducer;