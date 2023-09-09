<template>
<div id="profile">
  <Header />
  <profile-buttons />
</div>
  
</template>


<script>

import ProfileButtons from '../components/ProfileButtons'
import Header from '../components/Header'
export default {
    components: {
        Header,
        ProfileButtons
    },
};
</script>

<style>
#profile {
  background-image: url("https://img.freepik.com/free-vector/blank-blue-halftone-background_53876-114466.jpg"); /* Replace with the actual path to your image */
  background-size:cover; /* Adjust this property to control how the image covers the background */
  background-repeat: no-repeat;
  min-height: 100vh;
}
</style>