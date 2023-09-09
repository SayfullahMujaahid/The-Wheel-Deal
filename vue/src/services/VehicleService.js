import axios from 'axios';

export default {

    getVehicleForUser(userId) {
        return axios.get(`/profile/${userId}/vehicles`);
    },

    registerVehicle(userId, vehicle) {
        return axios.post(`/profile/${userId}/vehicles`, vehicle);
    }

}