
import './App.css';
import RegisterComponent from "./Components/RegisterComponent";
import {Route, Routes} from "react-router-dom";
import LoginComponent from "./Components/LoginComponent";
import HomeComponent from "./Components/HomeComponent";

function App() {
    return (
        <div className="App">
            <Routes>
                <Route path = "/home" element = {<HomeComponent/>}/>
                <Route path = "/register" element = {<RegisterComponent/>}/>
                <Route path = "/login" element = {<LoginComponent/>}/>
            </Routes>
        </div>
    );
}

export default App;
