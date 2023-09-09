<template>
  <div class="home">
    <Header />
    <home-tab />
    <services-list />
    <about-us />
    <contact-us />
    
  </div>
</template>

<script>
import Header from '../components/Header';
import HomeTab from '../components/HomeTab';
import ServicesList from '../components/ServicesList';
import AboutUs from '../components/AboutUs';
import ContactUs from '../components/ContactUs';

export default {
  components: {
    Header,
    HomeTab,
    ServicesList,
    AboutUs,
    ContactUs
  }

};
</script>

<style>

  body {
    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif, sans-serif;
    line-height: 1.6;
    margin: 0;
    padding: 0;
    background-color: #ffffff;
    color: rgb(255, 255, 255);
    background-size:cover; /* Adjust this property to control how the image covers the background */
    background-repeat: no-repeat;
    background-image: url("https://cdn.cnn.com/cnnnext/dam/assets/220721175751-woman-mechanic-stock-super-tease.jpg");
    /* background-image: url("https://img.freepik.com/free-vector/blank-blue-halftone-background_53876-114466.jpg"); Replace with the actual path to your image */
}
  
  
  h2 {
    text-align: center;
    color: #ffffff;
    padding-bottom: 20px;
    padding-top: 20px;
    
  }
  .service-list li select {
    width: 100%;
    padding: 0.7rem;
    border: 2px solid rgb(255, 255, 255);
    border-radius: 4px;
    background-color: #555555;
    font-size: 0.8rem;
    color: rgb(219, 200, 200);
    transition: border-color 0.3s ease, background-color 0.3s ease;
  }
  
  .service-list li select:hover,
  .service-list li select:focus {
    border-color: #4a90e2;
    background-color: rgb(255, 255, 255);
    outline: none;
  }
  
  .service-list li select option {
    font-size: 1rem;
    color: rgb(255, 255, 255);
    background-color: rgb(131, 101, 131);
  }
  
  
  nav ul {
    list-style: none;
    margin: 0;
    padding: 0;
  }
  
  nav ul li {
    display: inline;
    margin-left: 1rem;
  }
  
  nav ul li a {
    color: #fff;
    text-decoration: none;
  }
  
  main {
    padding: 2rem;
  }
  
  .hero-container {
    text-align: center;
    background-color: #9e43a375;
    
    padding: 3rem;
    border-radius: 10px;
    box-shadow: 0 5px 15px rgb(135, 250, 4);
  }
  
  .hero-container h2 {
    font-size: 2rem;
    margin-bottom: 1rem;
    padding-bottom: 20px;
    
  }
  
  .cta-button {
    display: inline-block;
    background-color: #78009c;
    color: rgb(252, 252, 252);
    text-decoration: none;
    padding: 1rem 1rem;
    border-radius: 5px;
    transition: background-color 0.1s ease;
    grid-column: span 2;
    
  }
  
  .cta-button:hover {
    background-color: #0400ff;
  }
  
  
  .services-container {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 1rem;
  }
  
  .services-container .service-item {
    background-color: #cffd01;
    color: #fff;
    padding: 2rem;
    border-radius: 10px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
  }
  
  .services-container .service-item h3 {
    font-size: 1.5rem;
    margin-bottom: 1rem;
  }
  
  .about-container {
    text-align: center;
    
    background-color: #6a336de7;
    color: #fff;
    padding: 3rem;
    /* border-radius: 10px; */
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
  }
  
  .about-container h2 {
    font-size: 2rem;
    margin-bottom: 1rem;
  }
  
  
  /* ... Your existing CSS styles ... */
  
  .service-list {
    list-style: none;
    padding: 0;
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 2rem;
  }
  
  
  .service-list li {
    background-color: #8208b393;
    color: #fff;
    padding: 2rem;
    border-radius: 10px;
    
  }
  
  .service-list li img {
    max-width: 100%;
    border-radius: 5px;
    margin-bottom: 1rem;
  }
  
  .service-list li h3 {
    font-size: 1.5rem;
    margin-bottom: 1rem;
  }
  /* Center the submit button */
.services-container form {
    text-align: center;
  }
  
  .services-container button {
    display: inline-block;
    margin-top: 1rem;
  }
  .header-container nav ul li a[href="/profile"] {
    color: #fff;
    text-decoration: none;
    
    transition: color 0.1s ease;
  }
  
  .header-container nav ul li a[href="/profile"]:hover {
    color: #ff7b00;
  }

  .header-container nav ul li a[href="/login"] {
    color: #fff;
    text-decoration: none;
    
    transition: color 0.1s ease;
  }
  
  .header-container nav ul li a[href="/login"]:hover {
    color: #ff7b00;
  }


  .header-container nav ul li a[href="/#contact"] {
    color: #fff;
    
    
    transition: color 0.1s ease;
  }
  
  .header-container nav ul li a[href="/#contact"]:hover {
    color: #ff7b00;
  }



  .header-container nav ul li a[href="/#about"] {
    color: #fff;
    
    
    transition: color 0.1s ease;
  }
  
  .header-container nav ul li a[href="/#about"]:hover {
    color: #ff7b00;
  }





  .header-container nav ul li a[href="/#services"] {
    color: #fff;
    
    
    transition: color 0.1s ease;
  }
  
  .header-container nav ul li a[href="/#services"]:hover {
    color: #ff7b00;
  }


.header-container nav ul li a[href="/"] {
    color: #fff;
    
    
    transition: color 0.1s ease;
  }
  
  .header-container nav ul li a[href="/"]:hover {
    color: #ff7b00;
  }
   
</style>