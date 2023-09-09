<template>
  <div class="appointment-form">
    <Header />
    <div class="form-container">
      <h1>Create Appointment</h1>

      <form @submit.prevent="createAppointment">
        <div class="form-group-vehicle">
          <label for="vehicle">Select Vehicle:</label>
          <select v-model="selectedVehicle" id="vehicle">
            <option value="-1">Select a vehicle</option>
            <option
              v-for="vehicle in savedVehicles"
              :key="vehicle.vehicleId"
              :value="vehicle.vehicleId"
            >
              {{ vehicle.make }} {{ vehicle.model }} ({{ vehicle.year }})
            </option>
          </select>
        </div>
        <!-- Add New Vehicle Drop Down -->
        <button @click="toggleAddNewVehicle">Add New Vehicle</button>

        <div v-if="showAddNewVehicle" class="add-new-vehicle-form">
          <form @submit.prevent="addNewVehicle">
            <div class="form-group">
              <label for="make">Make:</label>
              <select v-model="newVehicle.make" id="make" @change="makeChanged">
                <option value="">Make</option>
                <option
                  v-for="make in availableMakes"
                  :key="make.name"
                  :value="make.name"
                >
                  {{ make.name }}
                </option>
              </select>
            </div>
            <div class="form-group">
              <label for="model">Model:</label>
              <select v-model="newVehicle.model" id="model">
                <option value="">Model</option>
                <option
                  v-for="model in availableModels"
                  :key="model.name"
                  :value="model.name"
                >
                  {{ model.name }}
                </option>
              </select>
            </div>
            <div class="form-group">
              <label for="year">Year:</label>
              <select id="year" v-model="newVehicle.year" required>
                <option value="">Year</option>
                <option v-for="year in yearRange" :key="year" :value="year">
                  {{ year }}
                </option>
              </select>
            </div>
            <div class="form-group">
              <label for="color">Color:</label>
              <input type="text" v-model="newVehicle.color" id="color" />
            </div>
            <div class="form-group">
              <label for="plateNumber">Plate Number:</label>
              <input
                type="text"
                v-model="newVehicle.plateNumber"
                id="plateNumber"
              />
            </div>
            <div class="form-group">
              <label for="mileage">Mileage:</label>
              <input type="number" v-model="newVehicle.mileage" id="mileage" />
            </div>
            <button>Add Vehicle</button>
          </form>
        </div>
        <!-- Drop Down End -->
        <div class="form-group">
          <label for="appointmentDate">Appointment Date:</label>
          <input type="date" v-model="appointmentDate" id="appointmentDate" />
        </div>
        <div class="form-group">
          <label for="appointmentTime">Vehicle Drop Off Time:</label>
          <input type="time" v-model="appointmentTime" id="appointmentTime" />
        </div>
        <button type="submit">Book Appointment</button>
      </form>
    </div>
  </div>
</template>

<script>
import Header from '../components/Header';
import CarApiService from '../services/CarApiService';
import VehicleService from '../services/VehicleService';
import WorkOrderService from '../services/WorkOrderService';

export default {
  components: {
    Header,
  },
  data() {
  const endYear = 2000;
  const startYear = 2023;
  const yearRange = Array.from({ length: startYear - endYear + 1 }, (_, index) => startYear - index)


    return {
      selectedVehicle: -1,
      availableMakes: [], // Your available makes from API should create a drop down list
      availableModels: [], // Your available models from API
      yearRange,
      showAddNewVehicle: false,
      newVehicle: {
        make: "",
        model: "",
        year: null,
        color: "",
        plateNumber: "",
        mileage: null,
      },
      appointmentDate: "",
      appointmentTime: "",
      savedVehicles: []
    };
  },
  methods: {
    createAppointment() {
      const vehicle = this.savedVehicles.find(vehicle => vehicle.vehicleId === this.selectedVehicle);
      const services = this.$store.state.selectedServices;

      const workOrderDto = {
        vehicle,
        services
      };

      WorkOrderService
        .createWorkOrder(this.$store.state.user.id, workOrderDto)
        .then(() => {
          this.$router.push('/');
        });


      //   const appointmentDetails =
      //     vehicleId: this.selectedVehicle,
      //     date: this.appointmentDate,
      //     time: this.appointmentTime
      // You can perform further actions with the appointment details, like sending to a server or updating a database.
    },
    toggleAddNewVehicle() {
      this.showAddNewVehicle = !this.showAddNewVehicle;
      this.newVehicle = {
        make: "",
        model: "",
        year: null,
        color: "",
        plateNumber: "",
        mileage: null,
      };
    },
    addNewVehicle() {
      VehicleService.registerVehicle(this.$store.state.user.id, this.newVehicle)
        .then((response) => {
          // Assuming the API returns the newly created vehicle object
          this.savedVehicles.push(response.data);
          this.toggleAddNewVehicle();
        })
        .catch((error) => {
          console.error("Error adding new vehicle:", error);
        });
    },

    makeChanged() {
      CarApiService.getModels(this.newVehicle.make)
        .then((response) => {
          this.availableModels = response.data;
        })
        .catch((error) => {
          console.error("Error fetching models:", error);
        });

    }
},
created() {
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

    VehicleService
    .getVehicleForUser(this.$store.state.user.id)
    .then(response => {
      this.savedVehicles = response.data;
    });

}
  };


</script>

<style scoped>
.appointment-form {
  height: 100vh;
}
.add-new-vehicle-form {
  margin-top: 20px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
  
}

.form-container {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid rgb(221, 221, 221);
  background-color: #f8f8f8cc;
  margin-top: 3%;
}

h1 {
  font-size: 24px;
  margin-bottom: 20px;
  color: #555555;
}

.form-group {
  margin-bottom: 15px;
  width: 95%;
  color: #555555;
  
}
#vehicle {
  width: 99%;
}

label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
  color: #555555;
}

select,
input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>