import axios from "axios";

export default {
    getMakes() {
        return axios.get('/makes')
        .catch(e => {
            console.error("Could not get Makes", e);
            throw e;
        });
    },

    getModels(make) {
        return axios.get('/models?make=' + make)
        .catch(e => {
            console.error("Could not get Models", e);
            throw e;
        });
    }
}