<template>
  <div class="vehicle-form-container">
    <h1>Registered Vehicles</h1>
    <div class="vehicle-list">
      <div v-for="vehicle in registeredVehicles" :key="vehicle.id" class="vehicle">
        <p><strong>Make:</strong> {{ vehicle.make }}</p>
        <p><strong>Model:</strong> {{ vehicle.model }}</p>
        <p><strong>Year:</strong> {{ vehicle.year }}</p>
        <p><strong>Plate No:</strong> {{ vehicle.plateNumber }}</p>
      </div>
    </div>
    
    <h2>Register New Vehicle</h2>
    <form @submit.prevent="registerVehicle" class="new-vehicle-form">
      <div class="form-group">
          <label for="make">Make:</label>
          <select v-model="newVehicle.make" id="make" @change="makeChanged">
            <option value="">Make</option>
            <option v-for="make in availableMakes" :key="make" :value="make.name">{{ make.name }}</option>
          </select>
        </div>
      <div class="form-group">
          <label for="model">Model:</label>
          <select v-model="newVehicle.model" id="model">
            <option value="">Model</option>
            <option v-for="model in availableModels" :key="model" :value="model.name">{{ model.name }}</option>
          </select>
        </div>
       <div class="form-group">
    <label for="year">Year:</label>
    <select id="year" v-model="newVehicle.year" required>
      <option value="">Year</option>
      <option v-for="year in yearRange" :key="year" :value="year">{{ year }}</option>
    </select>
  </div>
      <div class="form-group">
        <label for="color">Color:</label>
        <input type="text" id="color" v-model="newVehicle.color" required />
      </div>
      <div class="form-group">
        <label for="plate_number">Plate No:</label>
        <input type="text" id="plate_number" v-model="newVehicle.plateNumber" required />
      </div>
      <div class="form-group">
        <label for="mileage">Mileage:</label>
        <input type="number" id="mileage" v-model="newVehicle.mileage" required />
      </div>
      <button type="submit">Register Vehicle</button>
    </form>
  </div>
</template>

<script>
import CarApiService from '../../services/CarApiService';
import VehicleService from '../../services/VehicleService';

export default {

data() {
const endYear = 2000;
const startYear = 2023;
const yearRange = Array.from({ length: startYear - endYear + 1 }, (_, index) => startYear - index)

    return {
      registeredVehicles: [],
      availableMakes:[],
      availableModels:[],
      yearRange,
      newVehicle: {
        make: '',
        model: '',
        year: '',
        color: '',
        plateNumber: '',
        mileage: ''
      }
    };
  },
  methods: {
    
    
    registerVehicle() {
      // Add the new vehicle to the registeredVehicles array
      this.registeredVehicles.push({
        id: this.registeredVehicles.length + 1,
        make: this.newVehicle.make,
        model: this.newVehicle.model,
        year: this.newVehicle.year,
        color: this.newVehicle.color,
        plateNumber: this.newVehicle.plateNumber,
        mileage: this.newVehicle.mileage
        // ...other properties...
      });

      VehicleService.registerVehicle(this.$store.state.user.id, this.newVehicle);
      
      // Reset the newVehicle object
      this.newVehicle = {
        make: '',
        model: '',
        year: '',
        color: '',
        plateNumber: '',
        mileage: ''
      };
    },
    makeChanged() {
      CarApiService.getModels(this.newVehicle.make)
        .then(response => {
          this.availableModels = response.data; 
        })
        .catch(error => {
          console.error("Error fetching models:", error);
        });

    },
  },

  created() {
      VehicleService
    .getVehicleForUser(this.$store.state.user.id)
    .then(response => {
      this.registeredVehicles = response.data;
    });
    
   CarApiService.getMakes()
    .then(response => {
      this.availableMakes = response.data; 
    })
    .catch(error => {
      console.error("Error fetching makes:", error);
    });

  CarApiService.getModels()
    .then(response => {
      this.availableModels = response.data; 
    })
    .catch(error => {
      console.error("Error fetching models:", error);
    });
  }
  };

</script>

<style scoped>
html, body {
  height: 100%;
  margin: 0;
  padding: 0;
}

body {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  margin: 0;
  background-size: cover;
  background-repeat: no-repeat;
}

.vehicle-form-container {
  max-width: 800px;
  width: 100%;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  background-color: #f8f8f8cc;
  border-radius: 8px;
  box-sizing: border-box;
}

h1 {
  font-size: 24px;
  margin-bottom: 20px;
  color: #333333;
  text-align: center;
}

.vehicle-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.vehicle {
  border: 1px solid rgb(0, 0, 0);
  padding: 10px;
  margin: 10px;
  background-color: #3b7d9dcc;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
}

h2 {
  font-size: 20px;
  margin-top: 30px;
  color: #2b2db4;
  text-align: center;
}

.new-vehicle-form {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
  margin-top: 10px;
  margin: 0 -10px; /* Add negative margin to adjust the gap between boxes and border */
}

.form-group {
  display: flex;
  flex-direction: column;
  margin-left: 30px;
}

label {
  margin-bottom: 5px;
  margin-left: 20px;
  text-align: left;
  color: #555555;
}
select,
input {
  padding: 8px;
  width: 90%;
  border: 1px solid rgb(109, 107, 107);
  border-radius: 4px;
  text-align: center;
}

button {
  padding: 8px 12px;
  background-color: #002df3;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
  text-align: center;
  grid-column: span 2;
  width: 100%; /* Stretch the button to extend from one border to the other */
}

button:hover {
  background-color: #0056b3;
}
</style>


