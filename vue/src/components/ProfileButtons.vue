<template>
  <div id="profile-page">
      <Header />
    <div class="profile-options">
      <div class="option" @click="showSection('account')">
        <i class="fas fa-user"></i>
        <span>Edit Account</span>
      </div>
      <div class="option" @click="showSection('vehicles')">
        <i class="fas fa-car"></i>
        <span>Registered Vehicles</span>
      </div>
      <div class="option" @click="showSection('services')">
        <i class="fas fa-wrench"></i>
        <span>Current Services</span>
      </div>
      <div class="option" @click="showSection('invoices')">
        <i class="fas fa-file-invoice"></i>
        <span>Invoices</span>
      </div>
      
    </div>
    <!-- Show Sections -->
    <div class="profile-section" v-if="activeSection === 'account'">
      <!-- Edit Account Section -->
      <edit-account />
    </div>
    
    <div class="profile-section" v-else-if="activeSection === 'vehicles'">
      <!-- Registered Vehicles Section -->
      <registered-vehicles />
    </div>
    <div class="profile-section" v-else-if="activeSection === 'services'">
      <!-- Current Services Section -->
      <current-services />
    </div>
    <div class="profile-section" v-else-if="activeSection === 'invoices'">
      <!-- Invoices Section -->
      <Invoices />
    </div>
  </div>
</template>

<script>
import EditAccount from '../components/ProfileSelections/EditAccount';
import CurrentServices from '../components/ProfileSelections/CurrentServices';
import RegisteredVehicles from '../components/ProfileSelections/RegisteredVehicles';
import Invoices from '../components/ProfileSelections/Invoices';

// import profileService from '../services/ProfileService.js';

export default {
  components: {
    EditAccount,
    CurrentServices,
    RegisteredVehicles,
    Invoices
  },

  // created() {
  //   profileService.getProfile().then(response => {
  //       const profileData = response.data;
  //   })
  // },

  data() {
    return {
      newUsername: '',
      newPassword: '',
      activeSection: 'account',
    };
  
  },

  methods: {
    updateAccount() {
      // Logic to update account details
    },
    showSection(section) {
      this.activeSection = section;
    }
  },
};
</script>

<style scoped>
#profile-page {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  padding: 20px;
}


.profile-options {
  display: flex;
  justify-content: center;
  gap: 50px;
  margin-top: 50px;
  margin-bottom: 20px;
}

.option {
  cursor: pointer;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.option i {
  font-size: 34px;
  margin-bottom: 5px;
}

.profile-section {
  
  margin-top: 20px;
}

.profile-section.active {
  display: block;
}


/* Account Form CSS */
.account-form {
  width: 100%;
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
}

.form-input-group {
  margin-bottom: 15px;
}

label {
  display: block;
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 5px;
  color: #555;
}

input[type="text"],
input[type="password"] {
  width: 100%;
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