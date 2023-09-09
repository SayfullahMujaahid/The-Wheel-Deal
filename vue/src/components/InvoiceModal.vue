<template>
  <div class="invoice">
    <h1>Invoice</h1>
    
    <div class="invoice-details">
      <p><strong>Work Order ID:</strong> {{ invoice.workOrder.workOrderId }}</p>
      <p><strong>Customer Name:</strong> {{ getCustomerName }}</p>
      <p><strong>Vehicle:</strong> {{ invoice.workOrder.vehicle.make }} {{ invoice.workOrder.vehicle.model }} (Plate No: {{ invoice.workOrder.vehicle.plateNumber }})</p>
    </div>
    
    <table class="services-table">
      <thead>
        <tr>
          <th>Services</th>
          <th>Price</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(serviceStatus, index) in completedServices" :key="index">
          <td>{{ serviceStatus.service.serviceDescription }}</td>
          <td>${{ serviceStatus.service.price }}</td>
        </tr>
      </tbody>
      <tfoot>
        <tr>
          <th>Total:</th>
          <td>{{ total }}</td>
        </tr>
      </tfoot>
    </table>
    
    <div class="paid-checkbox">
        <button v-show="invoiceIsNotPaid" @click="payInvoice">Mark as Paid</button>
      <!-- <label>
        <input type="checkbox" v-model="paid" @click="showSubmitInvoiceBtn(submitInvoiceBtn)"> Paid
      </label> -->
    </div>
  </div>
</template>

<script>
import InvoiceService from '../services/InvoiceService';

export default {
  props: ['invoice'],

  methods: {
      showSubmitInvoiceBtn(submitInvoiceBtn) {
          this.submitInvoiceBtn = !submitInvoiceBtn;
      },

      payInvoice() {
        const updatedInvoice = {...this.invoice};
        updatedInvoice.paid = true;
        InvoiceService
          .updateInvoice(this.invoice.invoiceID, updatedInvoice)
          .then(() => {
            this.$emit('invoice-paid');
        })
        this.$emit('close-modal');
      }
  },
  computed: {
    invoiceIsNotPaid() {
      return this.invoice.paid === false;
    },

    total() {
      return "$" + this.completedServices.reduce((total, serviceStatus) => total + serviceStatus.service.price, 0).toFixed(2);
    },

    getCustomerName() {
      const customerUser = this.invoice.user;
      return `${customerUser.firstName} ${customerUser.lastName}`;
    },

    completedServices() {
      return this.invoice.workOrder.serviceStatuses.filter(ss => ss.status.description === 'Completed');
    }

  },
};
</script>

<style scoped>
.invoice {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ffffff;
  background-color: rgba(143, 142, 142, 0.8);
}

.invoice-details {
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

.paid-checkbox {
  text-align: right;
  margin-top: 20px;
}
</style>
