<template>
    <section id="contact">
      <div class="contact-container">
        <h2>Contact Us</h2>
        <form>
          <input type="text" placeholder="Name">
          <input type="email" placeholder="Email">
          <textarea placeholder="Message"></textarea>
          <button type="submit">Send Message</button>
        </form>
      </div>
    </section>
</template>

<script>
export default {

}
</script>

<style scoped>
.contact-container {
    
    padding-top: 20px;
    padding-bottom: 20px;
    text-align: center;
    
    color: #fff;
    padding: 3rem;
    border-radius: 10px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
  }
  
  .contact-container h2 {
    display: flex;
    flex-direction: column;
    font-size: 2rem;
    margin-bottom: 1rem;
  }
  
  form input,
  form textarea,
  form button {
    /* max-width: 600px; */
    display: flex;
    width: 50%;
    padding: 1rem;
    border: none;
    border-radius: 5px;
    margin-bottom: 1rem;
    justify-content: center;
    margin-left: 25%;
    
  }
  form textarea {
    height: 150px;
  }
  
  
  form button {
    background-color: #56ff47;
    color: rgb(0, 0, 0);
    cursor: pointer;
    transition: background-color  ease;
  }
  
  form button:hover {
    background-color: #2eff74;
  }
</style>