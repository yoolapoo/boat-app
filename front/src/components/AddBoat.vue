<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="title">Title</label>
        <input
            type="text"
            class="form-control"
            id="title"
            required
            v-model="boat.title"
            name="title"
        />
      </div>

      <div class="form-group">
        <label for="description">Description</label>
        <input
            class="form-control"
            id="description"
            required
            v-model="boat.description"
            name="description"
        />
      </div>

      <button @click="saveBoat" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newBoat">Add</button>
    </div>
  </div>
</template>

<script>
import BoatDataService from "../services/BoatDataService";

export default {
  name: "add-boat",
  data() {
    return {
      boat: {
        id: null,
        title: "",
        description: "",
        published: false
      },
      submitted: false
    };
  },
  methods: {
    saveBoat() {
      const data = {
        title: this.boat.title,
        description: this.boat.description
      };

      BoatDataService.create(data)
          .then(response => {
            this.boat.id = response.data.id;
            console.log(response.data);
            this.submitted = true;
          })
          .catch(e => {
            console.log(e);
          });
    },

    newBoat() {
      this.submitted = false;
      this.boat = {};
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>