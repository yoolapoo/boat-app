<template>
  <div v-if="currentBoat" class="edit-form">
    <h4>Boat</h4>
    <form>
      <div class="form-group">
        <label for="title">Title</label>
        <input type="text" class="form-control" id="title"
               v-model="currentBoat.title"
        />
      </div>
      <div class="form-group">
        <label for="description">Description</label>
        <input type="text" class="form-control" id="description"
               v-model="currentBoat.description"
        />
      </div>

      <div class="form-group">
        <label><strong>Status:</strong></label>
        {{ currentBoat.published ? "Published" : "Pending" }}
      </div>
    </form>

    <button class="badge badge-primary mr-2"
            v-if="currentBoat.published"
            @click="updatePublished(false)"
    >
      UnPublish
    </button>
    <button v-else class="badge badge-primary mr-2"
            @click="updatePublished(true)"
    >
      Publish
    </button>

    <button class="badge badge-danger mr-2"
            @click="deleteBoat"
    >
      Delete
    </button>

    <button type="submit" class="badge badge-success"
            @click="updateBoat"
    >
      Update
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
    <p>Please click on a Boat...</p>
  </div>
</template>

<script>
import BoatDataService from "../services/boat.service";

export default {
  name: "Boat",
  data() {
    return {
      currentBoat: null,
      message: ''
    };
  },
  methods: {
    getBoat(id) {
      BoatDataService.get(id)
          .then(response => {
            this.currentBoat = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },

    updatePublished(status) {
      var data = {
        id: this.currentBoat.id,
        title: this.currentBoat.title,
        description: this.currentBoat.description,
        published: status
      };

      BoatDataService.update(this.currentBoat.id, data)
          .then(response => {
            this.currentBoat.published = status;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },

    updateBoat() {
      BoatDataService.update(this.currentBoat.id, this.currentBoat)
          .then(response => {
            console.log(response.data);
            this.message = 'The Boat was updated successfully!';
          })
          .catch(e => {
            console.log(e);
          });
    },

    deleteBoat() {
      BoatDataService.delete(this.currentBoat.id)
          .then(response => {
            console.log(response.data);
            this.$router.push({ name: "Boats" });
          })
          .catch(e => {
            console.log(e);
          });
    }
  },
  mounted() {
    this.message = '';
    this.getBoat(this.$route.params.id);
  }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>