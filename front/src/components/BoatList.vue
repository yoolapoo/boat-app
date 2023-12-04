<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by title"
               v-model="title"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
                  @click="searchTitle"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Boats List</h4>
      <ul class="list-group">
        <li class="list-group-item"
            :class="{ active: index === currentIndex }"
            v-for="(Boat, index) in Boats"
            :key="index"
            @click="setActiveBoat(Boat, index)"
        >
          {{ Boat.title }}
        </li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllBoats">
        Remove All
      </button>
    </div>
    <div class="col-md-6">
      <div v-if="currentBoat">
        <h4>Boat</h4>
        <div>
          <label><strong>Title:</strong></label> {{ currentBoat.title }}
        </div>
        <div>
          <label><strong>Description:</strong></label> {{ currentBoat.description }}
        </div>
        <div>
          <label><strong>Status:</strong></label> {{ currentBoat.published ? "Published" : "Pending" }}
        </div>

        <a class="badge badge-warning"
           :href="'/Boats/' + currentBoat.id"
        >
          Edit
        </a>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Boat...</p>
      </div>
    </div>
  </div>
</template>

<script>
import BoatDataService from "../services/boatDataService";

export default {
  name: "Boats-list",
  data() {
    return {
      Boats: [],
      currentBoat: null,
      currentIndex: -1,
      title: ""
    };
  },
  methods: {
    retrieveBoats() {
      BoatDataService.getAll()
          .then(response => {
            this.Boats = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },

    refreshList() {
      this.retrieveBoats();
      this.currentBoat = null;
      this.currentIndex = -1;
    },

    setActiveBoat(Boat, index) {
      this.currentBoat = Boat;
      this.currentIndex = index;
    },

    removeAllBoats() {
      BoatDataService.deleteAll()
          .then(response => {
            console.log(response.data);
            this.refreshList();
          })
          .catch(e => {
            console.log(e);
          });
    },

    searchTitle() {
      BoatDataService.findByTitle(this.title)
          .then(response => {
            this.Boats = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    }
  },
  mounted() {
    this.retrieveBoats();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>