<template>
  <div class="current-services-form-container">
      <h1>Current Services</h1>
      <div class="vehicle-selection-and-add-new-button">
           <label for="vehicle-selection">Vehicle:</label>
      <span>
        <select name="saved-vehicles" id="vehicle-selection" v-model="selectedVehicleId">
          <option value="-1">Select Vehicle</option>
          <!-- below option should show saved users saved vehicles -->
          <option v-for= "vehicle in registeredVehicles" :key="vehicle.id" class="vehicles-drop-down" :value="vehicle.vehicleId">{{vehicle.make}} {{vehicle.model}}</option>
        </select>
      </span>
          <span>
             <button v-on:click="$parent.showSection('vehicles')">Add New</button>
          </span>
      </div>
      
    <div v-for="service in services" :key="service.serviceId" class="current-services-list">
      <div class="service-info">
        <h4>{{ service.description }}</h4>
        <p><strong>Price:</strong> {{ service.price }}</p>
        <!-- <p><strong>Qty:</strong> {{ service.quantity }}</p> -->
      </div>
      <!-- <div class="service-total">
        <p><strong>Total:</strong> {{ calculateTotal(service) }}</p>
      </div> -->
      <div class="service-status">
        <p><strong>Status:</strong> {{ service.status }}</p>
      </div>
    </div>
    
  </div>
      

  
</template>

<script>
import VehicleService from '../../services/VehicleService';
import WorkOrderService from '../../services/WorkOrderService';
export default {
  created() {
  VehicleService
    .getVehicleForUser(this.$store.state.user.id)
    .then(response => {
      this.registeredVehicles = response.data;
    });

  WorkOrderService
    .getWorkOrderByUserId(this.$store.state.user.id)
    .then(response => {
      this.workOrders = response.data;
    });
},

    data() {
        return {
          registeredVehicles: [],
          workOrders: [],
          selectedVehicleId: -1
        };
        
    },
    methods: {
        calculateTotal(service) {
            service.price * service.quantity
        },
        
    },
    computed: {
      services() {
        if (this.selectedVehicleId === -1) {
          return [];
        }

        const workOrderForVehicle = this.workOrders.find(wo => wo.vehicle.vehicleId === this.selectedVehicleId);
        
        if (workOrderForVehicle == null) {
          return [];
        }

        return workOrderForVehicle.serviceStatuses.map(obj => {
          return {
            serviceId: obj.service.serviceID,
            description: obj.service.serviceDescription,
            category: obj.service.category,
            price: obj.service.price,
            serviceTime: obj.service.serviceTime,
            statusId: obj.status.statusId,
            status: obj.status.description
          }
        })
      }
    }
   
}

</script>

<style scoped>
.current-services-form-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  background-color: #ffffffcc;
  border-radius: 8px;
  width: 900px;
}
option {
  color: rgb(0, 0, 0);
}

h1 {
  font-size: 24px;
  margin-bottom: 20px;
  color: #333333;
}

.vehicle-selection-and-add-new-button {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

label {
  margin-right: 10px;
}

select {
  padding: 8px;
  margin-right: 20px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  padding: 8px 12px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #0056b3;
}

.current-services-list {
  border: 1px solid #ddd;
  padding: 10px;
  margin-bottom: 10px;
  background-color: #2e1bd444;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
</style>