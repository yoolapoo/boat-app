import http from "../http-common";

class BoatService {
    getAll() {
        return http.get("/");
    }

    get(id) {
        return http.get(`/${id}`);
    }

    create(data) {
        return http.post("/create", data);
    }

    update(id, data) {
        return http.put(`/update/${id}`, data);
    }

    delete(id) {
        return http.delete(`/delete/${id}`);
    }

    deleteAll() {
        return http.delete(``);
    }

    findByTitle(title) {
        return http.get(`?title=${title}`);
    }
}

export default new BoatService();