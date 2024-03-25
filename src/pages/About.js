import React from "react";
import MultiplePizzas from "../assets/multiplePizzas.jpeg";
import "../styles/About.css";
function About() {
  return (
    <div className="about">
      <div
        className="aboutTop"
        style={{ backgroundImage: `url(${MultiplePizzas})` }}
      ></div>
      <div className="aboutBottom">
        <h1> ABOUT US</h1>
        <p>
        Located in the heart of downtown, our pizza restaurant offers a culinary experience that combines tradition with innovation. 
        With a warm and inviting ambiance, guests are greeted by the tantalizing aroma of freshly baked dough and savory toppings. 
        Our menu boasts a variety of artisanal pizzas, crafted with premium ingredients sourced locally whenever possible. 
        From classic Margherita to inventive combinations like BBQ chicken and pineapple, there's something to please every palate. 
        Vegetarian and vegan options are also available, ensuring inclusivity for all dietary preferences. 
        Complementing our pizzas is a carefully curated selection of salads, appetizers, and desserts, providing a well-rounded dining experience. 
        Whether you're grabbing a quick slice on the go or settling in for a leisurely meal with friends and family, 
        our pizza restaurant promises a memorable taste journey that keeps guests coming back for more.
         
        </p>
      </div>
    </div>
  );
}

export default About;