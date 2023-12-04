# Boat-App

![Boat Icon](/front/public/boat.ico)

The Boat Management project consists of two folders: `api` and `front-end`.

- The API is developed using Spring Boot 3.
- The front end is a VueJS application.

## Running the API

Before launching the Spring Application, ensure you run Docker Compose to create a MongoDB instance with pre-initialized data.

The API is accessible on port 8090.

[Local URL]('http://localhost:809O/')


## Running VueJS

```bash
cd c:\front
run install
npm run dev
```  

The front end is hosted on port 8091.

[Local URL]('http://localhost:8091/')

Two users are already registered:

**User Role:**
- Username: user
- Password: 12345678

**Admin Role:**
- Username: admin
- Password: 12345678

### Accessing API/Boats

Direct access to `api/boats` is restricted; authorization is required to access this route.
