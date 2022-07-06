import axios from "axios";
import image from './book.jpeg'

const HomeComponent = () => {

    return (
       <h3>
           Spor la citit!
           <img src={image} alt="Logo" />
       </h3>

    )

};


export default HomeComponent;