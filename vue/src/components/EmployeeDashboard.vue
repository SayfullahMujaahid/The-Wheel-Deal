<template>
  <div class="employee-dashboard">
    <h1>Employee Dashboard</h1>
    <!-- Filter Div -->

    <div class="workorder-filters">
      <h2>Filter Results:</h2>
      <label>Name:</label>
      <input  />
      <label>Completed:</label>
      <input type="checkbox"/>
    </div>
    <!--  -->
    <div class="workorder-list">
      <div
        v-for="workorder in workOrders"
        :key="workorder.workOrderId"
        class="workorder-item"
      >
        <div class="workorder-details">
          <p><strong>Work Order ID:</strong> {{ workorder.workOrderId }}</p>
          <p>
            <strong>Assigned Employee:</strong> {{ workorder.assignedEmployee }}
          </p>
          <p><strong>Customer Name:</strong> {{ getWorkOrderCustomerName(workorder) }}</p>
          <!-- Add The Vehicle Info Here -->
          <p><strong>Vehicle:</strong> Make: {{ workorder.vehicle.make }} Model: {{ workorder.vehicle.model }} Plate No: {{ workorder.vehicle.plateNumber }}</p>
        </div>

        <div class="services-list">
          <div
            v-for="(serviceStatus, index) in workorder.serviceStatuses"
            :key="index"
            class="service-item"
          >
            <p><strong>Service:</strong> {{ serviceStatus.service.serviceDescription }}</p>
            <p>
              <strong>Status:</strong>
              <select v-model="serviceStatus.status.statusId" @change="changeWorkOrderStatus(workorder.workOrderId, index, $event.target.value, $event.target.options[$event.target.selectedIndex].text)">
                <option value="3">Not Started</option>
                <option value="2">Declined</option>
                <option value="4">In Progress</option>
                <option value="5">Completed</option>
              </select>
            </p>
          </div>
        </div>

        <div class="workorder-checkbox">
          <button
            @click="createEditOrViewInvoice(workorder)"
          >
            <!-- When clicked "Create Invoice", it should switch to "Invoice Created" -->
            {{
              getInvoiceButtonText(workorder)
            }}
          </button>
        </div>
        <div class="modal " :class="{'is-active':workorder.id == activeModal}" >
          <div class="modal-background"></div>
          <div class="modal-content"> <Invoice-Modal v-on:invoice-paid="activeInvoice.paid = true" v-on:close-modal="activeModal=-1" v-if="activeInvoice != null" :invoice="activeInvoice" /></div>
          <button class="modal-close is-large" aria-label="close" @click="activeModal=-1"></button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import InvoiceModal from '../components/InvoiceModal';
import WorkOrderService from '../services/WorkOrderService';
import InvoiceService from '../services/InvoiceService';

// import VehicleService from '../services/VehicleService';
export default {

  created() {
    InvoiceService
     .getAllInvoices()
     .then(response => {
       this.invoices = response.data;
     });

    WorkOrderService
    .getAllWorkOrders()
    .then(response => {
      this.workOrders = response.data
    });
  },

  data() {
    return {
      activeInvoice: null,
      invoices: [],
      workOrders: [],
      services: [],
      activeModal:-1
    };
  },
  methods: {
    getInvoiceButtonText(workorder) {
      const invoiceForWorkOrder = this.invoices.find(invoice => invoice.workOrder.workOrderId === workorder.workOrderId);
      if (!invoiceForWorkOrder) {
        return 'Create Invoice';
      }
      
      if (invoiceForWorkOrder.paid) {
        return 'View Invoice';
      }

      return 'Edit Invoice';
    },
    
    changeWorkOrderStatus(workOrderId, serviceIndex, newStatusValue, newStatusText) {
      const workOrderToChange = this.workOrders.find(wo => wo.workOrderId === workOrderId);

      workOrderToChange.serviceStatuses[serviceIndex].status.statusId = Number.parseInt(newStatusValue);
      workOrderToChange.serviceStatuses[serviceIndex].status.description = newStatusText;
    
      WorkOrderService.updateWorkOrder(workOrderId, workOrderToChange);
    },

    createEditOrViewInvoice(workorder) {
      const invoiceForWorkOrder = this.invoices.find(invoice => invoice.workOrder.workOrderId === workorder.workOrderId);

      if (!invoiceForWorkOrder) {

        workorder.invoiceCreated = true; // Mark the workorder as invoice created

        const workOrderWithCompletedServices = workorder;
        workOrderWithCompletedServices.serviceStatuses = workOrderWithCompletedServices.serviceStatuses.filter(ss => ss.status.description === 'Completed')

        const invoice = {
          workOrder: workOrderWithCompletedServices,
          user: workorder.users.find(u => u.role === 'Customer')
        };

        InvoiceService
          .createInvoice(workorder.workOrderId, invoice)
          .then(response => {
            this.invoices.push(response.data);
            this.activeInvoice = response.data;
          })
      } else {
        this.activeInvoice = invoiceForWorkOrder;
      }

        this.activeModal = workorder.id; // set the id to match the model
    },

    getWorkOrderCustomerName(workorder) {
      if (workorder.users.length > 0) {
        return `${workorder.users[0].firstName} ${workorder.users[0].lastName}`;
      }
    },

      getServicesForWorkOrders(workorder) {
          const myArray = [];

        if (workorder.serviceStatuses.length > 0) {
          workorder.serviceStatuses.forEach(element => {

            const service = {
              serviceId: element.service.serviceID,
              serviceDescription: element.service.serviceDescription,
              statusId: element.status.statusId,
              statusDescription: element.status.description
            };

            myArray.push(service);

          });
        }

        return myArray;
      },

 

      
    },
    
  components: {
    InvoiceModal
  }
  
};
</script>

<style scoped>
.employee-dashboard {
  padding: 20px;
  min-height: 100vh;
}
h1 {
  font-size: 25px;
  font-weight: bolder;
}

.workorder-filters label {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  margin-bottom: 20px;
}

.workorder-filters h2 {
  display: flex;
  margin-bottom: 10px;
  /* padding-top: 10px; */
}

.workorder-filters label {
  margin-right: 5px;
  margin-bottom: 5px;
}

.workorder-item {
  margin-bottom: 20px;
  border: 1px solid #ccc;
  padding: 10px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: rgba(255, 255, 255, 0.8);
  border-radius: 8px;
}

.workorder-details {
  flex: 1;
  color: #555555;
}

.workorder-checkbox {
  flex-shrink: 0;
  color: #555555;
  font-size: 20px;
}
.services-list {
  color: #555555;
}
body {
  background-image: url("https://cdn.cnn.com/cnnnext/dam/assets/220721175751-woman-mechanic-stock-super-tease.jpg"); /* Replace with the actual path to your image */
  background-size: cover; /* Adjust this property to control how the image covers the background */
  background-repeat: no-repeat;
  min-height: 100vh;
}
</style>