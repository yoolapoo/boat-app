# boat-app

boat management project Two folders existed in this project an api and a front end

Api is made with spring boot 3
Front end is VueJS application

## To run API
You need to run docker-compose before,
launch Spring Application

The docker compose create a Mongo DB Instance
with some initialized data


## To Run VUEJS

```
cd c:\front
run install
npm run dev
``` 
There are two users already registered

User role:
user / 12345678

Admin role:
admin / 12345678


### You cannot access directly to api/boats
in fact this route is secured, you need to be authorized