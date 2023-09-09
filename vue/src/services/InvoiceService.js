import axios from "axios";

export default {

    getInvoiceForUser(userId) {
        return axios.get(`/profile/${userId}/invoices`);
    },

    createInvoice(workorderId, invoice) {
        return axios.post(`/workorders/${workorderId}/createinvoice`, invoice)
    },

    getAllInvoices() {
        return axios.get('/employeedashboard/invoices');
    },

    updateInvoice(invoiceId, invoice) {
        return axios.put(`employeedashboard/invoices/${invoiceId}`, invoice)
    }


}