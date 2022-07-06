import React, {useState} from "react";
import "./Register.css";
import axios from "axios";
import {useNavigate} from "react-router-dom";

const LOGIN_API = "http://localhost:8083/user/login"

const LoginComponent = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState(undefined);
    const [password, setPassword] = useState(undefined);

    const handleSubmit = (e) => {
        e.preventDefault();
        const loginUser = {
            email: email,
            password: password
        };
        addUser(loginUser);

    }

    const addUser = (loginUser) => {
        axios
            .post(`${LOGIN_API}`, loginUser)
            .then((response) => {
                console.log(response.data)
                alert("User registered");
                setEmail('');
                setPassword('');
                navigate('/home');
            })
            .catch((err) => {
                if (email == null || password == null){
                    alert("Fields are mandatory");
                    setEmail('');
                    setPassword('');
                }
                else {
                    alert("Incorrect username or password!");
                    setEmail('');
                    setPassword('');
                }
            });
    }

        return (
            <>
                <h2>Login</h2>
                <form>
                    <label>
                        Email:
                        <input type="text" name="name" value={email}
                               onChange={(event) => setEmail(event.target.value)}/>
                    </label>

                    <label>
                        Password:
                        <input type="text" name="name" value={password}
                               onChange={(event) => setPassword(event.target.value)}/>
                    </label>

                    <input type="submit" value="Submit"
                           onClick={handleSubmit}/>
                </form>
            </>

        )
    };

export default LoginComponent