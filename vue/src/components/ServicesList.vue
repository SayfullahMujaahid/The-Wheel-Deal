<template>
  <section id="services">
      <h2>                 Spend With Confidence</h2>
      <div class="services-container">
        <ul class="service-list">
          <li>
            <img src="https://t4.ftcdn.net/jpg/02/08/39/75/360_F_208397514_XGDiBH2uUdGVIdctkt4NMdhKkGlwbjxy.jpg" alt="Oil Change">
            <h3>Oil Change Services</h3>
            <p>Regular oil changes keep your engine running smoothly and improve performance.</p>
     
            <div v-for="service in oilChangeServices" :key="service.serviceID">
              <label for="" class="fancy-checkbox">
                <i class="far fa-check-circle" v-show="selectedServices.includes(service.serviceID)" @click="removeService(service.serviceID)"></i>
                <i class="far fa-circle" v-show="!selectedServices.includes(service.serviceID)" @click="addService(service.serviceID)"></i>
                <span  @click="toggleService(service.serviceID)"> {{ service.serviceDescription }} ${{service.price}} </span>
                <input type="checkbox" :value="service.serviceID" v-model="selectedServices" style="display:none;">
              </label>
            </div>
          </li>
          
          <li>
            <img src="https://media.istockphoto.com/id/1364951743/photo/checking-the-brake-discs-of-a-modern-car-vehicle-braking-system.jpg?s=612x612&w=0&k=20&c=QL4hik0Rin2dkoCLfQPLkwDfhiRTCD7gkHS7hTPtBdQ=" alt="Brake Repair">
            <h3> Brake Services</h3>
            <p>We offer expert brake repair services to ensure your ease-of-mind and safety on and off the road.</p>
            <div v-for="service in brakeServices" :key="service.serviceID">
              <label for="" class="fancy-checkbox">
                <i class="far fa-check-circle" v-show="selectedServices.includes(service.serviceID)" @click="removeService(service.serviceID)"></i>
                <i class="far fa-circle" v-show="!selectedServices.includes(service.serviceID)" @click="addService(service.serviceID)"></i>
                <span  @click="toggleService(service.serviceID)"> {{ service.serviceDescription }} ${{service.price}} </span>
                <input type="checkbox" :value="service.serviceID" v-model="selectedServices" style="display:none;">
              </label>
            </div>
          </li>
          <li>
            <img src="https://st2.depositphotos.com/1003098/8898/i/450/depositphotos_88985818-stock-photo-mechanic-fixing-car-tire-at.jpg" alt="Tire Replacement">
            <h3> Tire and Wheel Services</h3>
            <p> Our swift and Comprehensive tire services vastly improve your car's handling and safety.</p>
            <div v-for="service in wheelServices" :key="service.serviceID">
              <label for="" class="fancy-checkbox">
                <i class="far fa-check-circle" v-show="selectedServices.includes(service.serviceID)" @click="removeService(service.serviceID)"></i>
                <i class="far fa-circle" v-show="!selectedServices.includes(service.serviceID)" @click="addService(service.serviceID)"></i>
                <span  @click="toggleService(service.serviceID)"> {{ service.serviceDescription }} ${{service.price}} </span>
                <input type="checkbox" :value="service.serviceID" v-model="selectedServices" style="display:none;">
              </label>
            </div>
          </li>
          <li>
            <img src="https://media.istockphoto.com/id/1347150429/photo/professional-mechanic-working-on-the-engine-of-the-car-in-the-garage.jpg?s=612x612&w=0&k=20&c=5zlDGgLNNaWsp_jq_L1AsGT85wrzpdl3kVH-75S-zTU=" alt="Tire Replacement">
            <h3> Auto Repair Services </h3>
            <p> The check engine light can be intimidating, however our experts are the best out there.</p>
            <div v-for="service in repairServices" :key="service.serviceID">
              <label for="" class="fancy-checkbox">
                <i class="far fa-check-circle" v-show="selectedServices.includes(service.serviceID)" @click="removeService(service.serviceID)"></i>
                <i class="far fa-circle" v-show="!selectedServices.includes(service.serviceID)" @click="addService(service.serviceID)"></i>
                <span  @click="toggleService(service.serviceID)"> {{ service.serviceDescription }} ${{service.price}} </span>
                <input type="checkbox" :value="service.serviceID" v-model="selectedServices" style="display:none;">
              </label>
            </div>
          </li>
          <li>
            <img src="https://www.motortrend.com/uploads/f/102375678.jpg?fit=around%7C875:492" alt="Tire Replacement">
            <h3> Emissions & converters</h3>
            <p>  Keep yourself and your car clean by reducing your C02 emissions to UN standards.</p>
            <div v-for="service in emissionServices" :key="service.serviceID">
              <label for="" class="fancy-checkbox">
                <i class="far fa-check-circle" v-show="selectedServices.includes(service.serviceID)" @click="removeService(service.serviceID)"></i>
                <i class="far fa-circle" v-show="!selectedServices.includes(service.serviceID)" @click="addService(service.serviceID)"></i>
                <span  @click="toggleService(service.serviceID)"> {{ service.serviceDescription }} ${{service.price}}</span>
                <input type="checkbox" :value="service.serviceID" v-model="selectedServices" style="display:none;">
              </label>
            </div>
          </li>
          <li>
            <img src="https://media.istockphoto.com/id/1193247873/photo/handsome-mechanic-in-uniform.jpg?s=612x612&w=0&k=20&c=Ro-W77lna0nRHOkP0lFT0DcsZmWVUtJTeGERND3kiFA=" alt="Tire Replacement">
            <h3> Springs</h3>
            <p> Essential suspension components that absorb shocks and maintain vehicle stability  .</p>
            <div v-for="service in suspensionServices" :key="service.serviceID">
              <label for="" class="fancy-checkbox">
                <i class="far fa-check-circle" v-show="selectedServices.includes(service.serviceID)" @click="removeService(service.serviceID)"></i>
                <i class="far fa-circle" v-show="!selectedServices.includes(service.serviceID)" @click="addService(service.serviceID)"></i>
                <span  @click="toggleService(service.serviceID)"> {{ service.serviceDescription }} ${{service.price}} </span>
                <input type="checkbox" :value="service.serviceID" v-model="selectedServices" style="display:none;">
              </label>
            </div>
          </li>
          <li>
            <img src="https://media.istockphoto.com/id/1395632834/photo/under-the-car-chassis-truck-drive-shaft-and-exhaust-chamber-silencer-muffler-system.jpg?s=612x612&w=0&k=20&c=YeTbBhLy4eqzKribEqMUVc6LjDrIukO5ORIfNZUwFhg=" alt="Tire Replacement">
            <h3>CV Joints and Driveshafts</h3>
            <p>Anything that moves you is our priority from the lowly screw to the rotating driveshafts, we have it all.</p>
            <div v-for="service in drivetrainServices" :key="service.serviceID">
              <label for="" class="fancy-checkbox">
                <i class="far fa-check-circle" v-show="selectedServices.includes(service.serviceID)" @click="removeService(service.serviceID)"></i>
                <i class="far fa-circle" v-show="!selectedServices.includes(service.serviceID)" @click="addService(service.serviceID)"></i>
                <span  @click="toggleService(service.serviceID)"> {{ service.serviceDescription }} ${{service.price}} </span>
                <input type="checkbox" :value="service.serviceID" v-model="selectedServices" style="display:none;">
              </label>
            </div>
          </li>
          <li>
            <img src="https://media.istockphoto.com/id/1321691449/photo/car-air-conditioner-check-service-leak-detection-fill-refrigerant-device-and-meter-liquid.jpg?s=612x612&w=0&k=20&c=nakqGI5FlvJ1GNVBoBigTLJqjYDgRg70JjHMdFuTU8c=" alt="Tire Replacement">
            <h3> Air Conditioning</h3>
            <p> Fight back against climate change with our special concoction of cooled compressed gasses.</p>
            <div v-for="service in acServices" :key="service.serviceID">
              <label for="" class="fancy-checkbox">
                <i class="far fa-check-circle" v-show="selectedServices.includes(service.serviceID)" @click="removeService(service.serviceID)"></i>
                <i class="far fa-circle" v-show="!selectedServices.includes(service.serviceID)" @click="addService(service.serviceID)"></i>
                <span  @click="toggleService(service.serviceID)"> {{ service.serviceDescription }} ${{service.price}} </span>
                <input type="checkbox" :value="service.serviceID" v-model="selectedServices" style="display:none;">
              </label>
            </div>
          </li>
          <li>
            <img src="https://media.istockphoto.com/id/1398301647/photo/smiling-mature-couple-giving-hotel-reception-their-cardkey.jpg?s=612x612&w=0&k=20&c=uAvLWOTCJelU06oQmPqQwzIgxBd_9vzXv9w38i2rYoU=" alt="Tire Replacement">
            <h3> Miscellaneous Services</h3>
            <p>Anything to make you and your highly effecient 4-wheeled (or 5-wheeled) partner confortable.</p>
            <div v-for="service in miscServices" :key="service.serviceID">
              <label for="" class="fancy-checkbox">
                <i class="far fa-check-circle" v-show="selectedServices.includes(service.serviceID)" @click="removeService(service.serviceID)"></i>
                <i class="far fa-circle" v-show="!selectedServices.includes(service.serviceID)" @click="addService(service.serviceID)"></i>
                <span @click="toggleService(service.serviceID)"> {{ service.serviceDescription }} ${{service.price}}</span>
                <input type="checkbox" :value="service.serviceID" v-model="selectedServices" style="display:none;">
              </label>
            </div>
          </li>
          <!-- ... Existing code ... -->
    <section id="services">
      
        <div class="services-container">
          <form @submit.prevent="submitForm"> <!-- Change 'submit_services.php' to your server-side script -->
            <ul class="service-list">
              <!-- ... Existing service items ... -->
            </ul>
            <div class="other-services">
              <!-- ... Other service options ... -->
            </div>
            <button type="submit" class="cta-button">Submit</button> <!-- Added submit button -->
          </form>
        </div>
      </section>
          <!-- Add more service items as needed -->
        </ul>
      </div>
    </section>
</template>

<script>
import serviceService from '../services/ServiceService';

export default {
  created() {
    serviceService
      .getAllServices()
      .then(response => {
        this.allServices = response.data;
      })
  },

  data() {
    return {
      allServices: [],

      selectedServices: []
    }
  },

  computed: {
    oilChangeServices() {
      return this.allServices.filter(service => service.category === 'Oil Change Services');
    },

    brakeServices() {
      return this.allServices.filter(service => service.category === 'Brake Services');
    },

    wheelServices() {
      return this.allServices.filter(service => service.category === 'Tire & Wheel Services');
    },

    repairServices() {
      return this.allServices.filter(service => service.category === 'Auto Repair Services');
    },

    emissionServices() {
      return this.allServices.filter(service => service.category === 'Exhaust & Catalytic Converters');
    },

    suspensionServices() {
      return this.allServices.filter(service => service.category === 'Springs');
    },

    drivetrainServices() {
      return this.allServices.filter(service => service.category === 'CV Joints & Driveshafts');
    },

    acServices() {
      return this.allServices.filter(service => service.category === 'Air Conditioning');

    },

    miscServices() {
      return this.allServices.filter(service => service.category === 'Miscellaneous Services');

    }
  },

  methods: {
    addService(id) {
      if (this.selectedServices.includes(id) === false) {
        this.selectedServices.push(id);
      }
    },
    removeService(id) {
      this.selectedServices = this.selectedServices.filter(theId => theId !== id)
    },
    toggleService(id) {
      if (this.selectedServices.includes(id)) {
        this.removeService(id);
      } else {
        this.addService(id);
      }
    },
    submitForm() {
      const selectedServiceObjects = [];

      this.selectedServices.forEach(serviceId => {
        this.allServices.forEach(serviceObject => {
          if (serviceId === serviceObject.serviceID) {
            selectedServiceObjects.push(serviceObject);
          }
        });
      });


      this.$store.commit('ADD_SELECTED_SERVICES', selectedServiceObjects);
      this.$router.push({ name:'appointment' });
    }
  }
}
</script>

<style>
  .fancy-checkbox i, .fancy-checkbox span {
    cursor: pointer;
    user-select: none;
  }

  h2 {
    font-size: 25px;
    
  }
</style>