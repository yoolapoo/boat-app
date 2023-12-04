import http from "../http-common";

class BoatDataService {
    getAll() {
        return http.get("/boats");
    }

    get(id) {
        return http.get(`/boats/${id}`);
    }

    create(data) {
        return http.post("/boats", data);
    }

    update(id, data) {
        return http.put(`/boats/${id}`, data);
    }

    delete(id) {
        return http.delete(`/boats/${id}`);
    }

    deleteAll() {
        return http.delete(`/boats`);
    }

    findByTitle(title) {
        return http.get(`/boats?title=${title}`);
    }
}

export default new BoatDataService();