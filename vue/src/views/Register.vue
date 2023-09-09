<template>
  <div id="register" class="register-page">
    <Header />
  <form @submit.prevent="register" class="register-form">
    <h1 class="register-title">Create an Account</h1>
    <div role="alert" v-if="registrationErrors" class="alert alert-danger">
      {{ registrationErrorMsg }}
    </div>
    <div class="form-input-group">
      <label for="username">Username:</label>
      <input type="text" id="username" v-model="user.username" required autofocus />
    </div>
    <div class="form-input-group">
      <label for="fname">First Name:</label>
      <input type="text" id="fname" v-model="user.first_name" required autofocus />
    </div>
    <div class="form-input-group">
      <label for="lname">Last Name:</label>
      <input type="text" id="lname" v-model="user.last_name" required autofocus />
    </div>
    <div class="form-input-group">
      <label for="password">Password:</label>
      <input type="password" id="password" v-model="user.password" required />
    </div>
    <div class="form-input-group">
      <label for="confirmPassword">Confirm Password:</label>
      <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
    </div>
    <div class="form-input-group">
      <label for="email">Email:</label>
      <input type="email" id="email" v-model="user.email" required autofocus />
    </div>
    <div class="form-input-group">
      <label for="phoneNumber">Phone Number:</label>
      <input type="tel" id="phoneNumber" v-model="user.phone" required autofocus />
    </div>
    <button type="submit" class="btn btn-primary">Create Account</button>
    <p class="login-link">
      <router-link :to="{ name: 'login' }">Already have an account? Log in</router-link>
    </p>
  </form>
</div>
</template>

<script>
import authService from '../services/AuthService';
import Header from '../components/Header';

export default {
  name: 'register',
  components: {
    Header
  },
  data() {
    return {
      user: {
        username: '',
        first_name: '',
        last_name: '',
        password: '',
        confirmPassword: '',
        email: '',
        phone: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.register-page {
  /* display: flex;
  justify-content: center;
  align-items: center; */
  min-height: 100vh;
  /* background-color: #f5f8fb; */
}

.register-form {
  width: 350px;
  padding: 20px;
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  margin: 0 auto;
  margin-top: 2rem;
  margin-bottom: 2rem;
}

.register-title {
  font-size: 24px;
  text-align: center;
  margin-bottom: 20px;
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
input[type="email"],
input[type="tel"],
input[type="password"] {
  width: 310px;
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

.login-link {
  margin-top: 15px;
  text-align: center;
  font-size: 14px;
  color: #777;
}

.alert {
  padding: 10px;
  border-radius: 4px;
  margin-bottom: 10px;
  font-size: 14px;
}

.alert-danger {
  background-color: #f8d7da;
  color: #721c24;
}

</style>
