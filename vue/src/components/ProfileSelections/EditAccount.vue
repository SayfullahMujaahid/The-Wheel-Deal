<template>
  <div class="registration-form-container">
    <form @submit.prevent="updateAccount" class="registration-form">
      <h2>Edit Account Information</h2>
      <div class="form-input-group">
        <label for="newUsername"><div> New Username:</div></label>
        <input type="text" id="newUsername" v-model="newUsername">
      </div>
      <div class="form-input-group">
        <label for="newPassword"><div> Current Password:</div></label>
        <input type="password" id="newPassword" v-model="newPassword" />
      </div>
      <div class="form-input-group">
        <label for="confirmNewPassword"><div> New Password:</div></label>
        <input type="password" id="confirmNewPassword" v-model="confirmNewPassword" />
      </div>
      <div class="form-input-group">
        <label for="email"><div> Email:</div></label>
        <input type="email" id="email" v-model="email" />
      </div>
      <div class="form-input-group">
        <label for=""><div> Phone Number:</div></label>
        <input type="tel" id="phoneNumber" v-model="phoneNumber" />
      </div>
      <button type="submit" class="btn btn-primary">Update Account</button>
    </form>
    
  </div>
</template>

<script>

export default {

}
</script>

<style scoped>

.registration-form-container {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  min-height: 100vh;
  
}

.registration-form {
  width: 100%;
  max-width: 400px;
  padding: 40px;
  background-color: rgba(255, 255, 255, 0.800);
  border-radius: 8px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  
}

.registration-form h2 {
  font-size: 24px;
  margin-bottom: 10px;
  color: #333;
}

.form-input-group {
  margin-bottom: 15px;
  margin-right: 20px;
}

label {
  display: block;
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 5px;
  color: #555;
}

input[type="text"],
input[type="password"],
input[type="tel"],
input[type="email"] {
  width: 320px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.btn-primary {
  display: block;
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
  transition: background-color 0.3s ease;
}

.btn-primary:hover {
  background-color: #0056b3;
}
</style>