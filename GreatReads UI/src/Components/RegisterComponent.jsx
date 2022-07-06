import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import "./Register.css";
import {roles} from "./Roles";
import {styled} from "@mui/material";


const REGISTER_API = "http://localhost:8083/user/register"

const RegisterComponent = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState(undefined);
    const [userType, setUserType] = useState(undefined);
    const [password, setPassword] = useState(undefined);
    const [firstName, setFirstName] = useState(undefined);
    const [lastName, setLastName] = useState(undefined);

    const [isOpen, setIsOpen] = useState(false);
    const [selectedOption, setSelectedOption] = useState(null);
    const toggling = () => setIsOpen(!isOpen);

    const handleSubmit = (e) => {
        e.preventDefault();
        const registeredUser = {
            email: email,
            userType: userType,
            password: password,
            firstName: firstName,
            lastName: lastName
        };
        addUser(registeredUser);


    }

    const addUser = (registeredUser) => {
        axios
            .post(`${REGISTER_API}`, registeredUser)
            .then((response) => {
                console.log(response.data)
                alert("User registered");
                setEmail('');
                setUserType('');
                setPassword('');
                setFirstName('');
                setLastName('');
                navigate('/home');
            })
            .catch((err) => {
               if (err.response?.status === 400) {
                    alert("Email used");
                   setEmail('');
                   setUserType('');
                   setPassword('');
                   setFirstName('');
                   setLastName('');
                } else {
                    alert("Registration failed");
                   setEmail('');
                   setUserType('');
                   setPassword('');
                   setFirstName('');
                   setLastName('');
                }
            }
)};

        const onOptionClicked = value => () => {
            setSelectedOption(value);
            setIsOpen(false);
        };

return (
    <>
        <h2>Register</h2>
        <form>
            <label>
                Email:
                <input type="text" name="name" value={email} onChange={(event) => setEmail(event.target.value)}/>
            </label>
            <label>
                Role:
                <input type="text" name="name" value={userType} onChange={(event) => setUserType(event.target.value)}/>
            </label>
            <label>
                Password:
                <input type="text" name="name" value={password} onChange={(event) => setPassword(event.target.value)}/>
            </label>

            <label>
                First name:
                <input type="text" name="name" value={firstName} onChange={(event) => setFirstName(event.target.value)}/>
            </label>

            <label>
                Last name:
                <input type="text" name="name" value={lastName} onChange={(event) => setLastName(event.target.value)}/>
            </label>

            <input type="submit" value="Submit"
                   onClick={handleSubmit}/>
        </form>
    </>
)
}
;
export default RegisterComponent;
