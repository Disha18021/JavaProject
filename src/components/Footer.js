import React from "react";
import InstagramIcon from "@material-ui/icons/Instagram";
import TwitterIcon from "@material-ui/icons/Twitter";
import FacebookIcon from "@material-ui/icons/Facebook";
import LinkedInIcon from "@material-ui/icons/LinkedIn";
import "../styles/Footer.css";

function Footer() {
  return (
    <div className="footer">
      <div className="socialMedia">
        <a href="https://www.instagram.com/bhavedisha" target="_blank" rel="noopener noreferrer">
        <InstagramIcon /> 
        </a>
        <TwitterIcon /> <FacebookIcon /> <LinkedInIcon />
      </div>
      <p> &copy; 2024 hotpizza.com</p>
    </div>
  );
}

export default Footer;