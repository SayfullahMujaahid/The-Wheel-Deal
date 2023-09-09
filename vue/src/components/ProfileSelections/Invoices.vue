<template>
  <div class="invoices-page">
    <h1>User Invoices</h1>
    <div class="invoices-list">
      <div v-for="invoice in invoices" :key="invoice.id" class="invoice">
        <div class="invoice-header">
         
          <div class="invoice-summary">
            <p><strong>Vehicle:</strong> {{ invoice.workOrder.vehicle.make }} {{ invoice.workOrder.vehicle.model }} {{invoice.workOrder.vehicle.year}}</p>
            <p><strong>Total:</strong> ${{calculateTotal(invoice)}} </p>
          </div>
           <button class="toggle-details-btn" @click="toggleDetails(invoice)">
            {{ isOpen ? 'Hide Details' : 'Show Details' }}
          </button>
        </div>
        <transition name="fade">
          <div v-if="isOpen" class="invoice-details">
            <!-- Invoice Button -->
             <div class="invoice-pop-up">
                <h1>Invoice</h1>
                
                <div class="invoice-details-pop-up">
                  <p><strong>Invoice No:</strong> {{ invoice.invoiceID }}</p>
                  <p><strong>Customer Name:</strong> {{ invoice.user.firstName }}  {{invoice.user.lastName}}</p>
                  <p><strong>Vehicle:</strong> {{ invoice.workOrder.vehicle.make }} {{ invoice.workOrder.vehicle.model }}</p>
                </div>
                
                <table class="services-table">
                  <thead>
                    <tr>
                      <th>Services</th>
                      <th>Price</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="(service, index) in invoice.workOrder.serviceStatuses" :key="index">
                      <td>{{ service.service.serviceDescription }}</td>
                      <td>$ {{ service.service.price }}</td>
                    </tr>
                  </tbody>
                  <tfoot>
                    <tr>
                      <th>Total:</th>
                      <td>{{ calculateTotal(invoice) }}</td>
                    </tr>
                  </tfoot>
    </table>
    </div>
    </div>
        </transition>
      </div>
    </div>
  </div>
</template>

<script>
import InvoiceService from '../../services/InvoiceService';

export default {

  created() {
    InvoiceService
    .getInvoiceForUser(this.$store.state.user.id)
    .then(response => {
        this.invoices = response.data
      })
    },
  
  data() {
    return {
      isOpen: false,
      invoices: [],
    };
  },
  methods: {
    toggleDetails(invoice) {
      invoice
      this.isOpen = !this.isOpen
    },
    calculateTotal(invoice) {
      let totalPrice = 0;
      invoice.workOrder.serviceStatuses.forEach(item => {
        totalPrice += parseFloat(item.service.price); 
      })
      return totalPrice.toFixed(2);
    },
    
  }
}
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
  background-image: url("https://cdn.cnn.com/cnnnext/dam/assets/220721175751-woman-mechanic-stock-super-tease.jpg");
  background-size: cover;
  background-repeat: no-repeat;
}

.invoices-page {
  max-width: 800px;
  width: 1000px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid rgb(253, 253, 253);
  background-color: #ffffffcc;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  box-sizing: border-box;
}

h1 {
  font-size: 24px;
  margin-bottom: 20px;
  color: #555555;
}

.invoices-list {
  display: flex;
  flex-direction: column;
}

.invoice {
  border: 1px solid #ddd;
  padding: 10px;
  margin-bottom: 10px;
  background-color: #9c9a9acc;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  
}

.invoice-header {
  display: flex;
  justify-content: space-between;
}

.toggle-details-btn {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
  border-radius: 20px;
}

.invoice-summary {
  display: flex;
  flex-direction: column;
  align-items: flex;
  border-radius: 333px;
}

.invoice-details {
  border-top: 1px solid rgb(18, 22, 243);
  margin-top: 10px;
  padding-top: 10px;
  
}

.purchase {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
  border-top: 1px solid rgb(38, 17, 226);
  padding-top: 10px;
}

.purchase-info {
  flex: 1;
}

.purchase-total {
  flex: 0.5;
}

strong {
  font-weight: bold;
  color: #007bff;
}

p {
  margin: 5px 0;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity .5s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}

/* New Invoice CSS */
.invoice-pop-up {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ffffff;
  background-color: rgba(143, 142, 142, 0.8);
}

.invoice-details-pop-up {
  margin-bottom: 20px;
  color: white;
}

.services-table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
  color: rgb(0, 0, 0);
  
}
label {
    padding: 10px
}

.services-table th,
.services-table td {
  padding: 8px;
  border: 1px solid rgb(255, 255, 255);
  text-align: left;
}
</style>
