import axios from "axios";

export default {

    getUsersByWorkorderId(workorderId) {
        return axios.get(`/workorder/${workorderId}/users`);
    },
    // is this right? does it require a userId path variable?
    updateUser(user) {
        return axios.put(`/profile/updateuser`, user)
    },

    createUser(user) {
        return axios.post(`/employeedashboard/createuser`, user)
    }
}