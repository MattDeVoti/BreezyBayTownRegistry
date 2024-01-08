# Town Registry
## Backend skill showcase
#### This is a server I'm building to showcase some of my backend development skills. It's a work in progress and I plan on updating it frequently.

###### This server handles registering citizens of a town and also information about towns to match them with. It uses springboot to accept information through API calls and backs up the data to MongoDB

## To Run

- Run TownRegistryApplication.class
- Run MongoDB community edition (or paid if you prefer) on port 27017 (Default MongoDB port, no configuration needed)
  - MongoDB install instructions : https://www.mongodb.com/docs/manual/installation/
- Using postman or some similar software, control the server through API calls on localhost:8080
  - Download Postman here : https://www.postman.com/downloads/

## API Calls
###
### Resident URL examples
#### Create Resident (Requires resident payload)
http://localhost:8080/api/nook/123456/registry/citizen/createResident/8901237654
#### Get Resident
http://localhost:8080/api/nook/123456/registry/citizen/getCitizen/8901237654
#### Delete Resident
http://localhost:8080/api/nook/123456/registry/citizen/deleteCitizen/8901237654
#### Update Resident (Requires resident payload)
http://localhost:8080/api/nook/123456/registry/citizen/updateResident/8901237654
###
### Employee URL examples
#### Create Employee (Requires employee payload)
http://localhost:8080/api/nook/123456/registry/citizen/createEmployee/5876390913
#### Get Employee
http://localhost:8080/api/nook/123456/registry/citizen/getCitizen/5876390913
#### Delete Employee
http://localhost:8080/api/nook/123456/registry/citizen/deleteCitizen/5876390913
#### Update Employee (Requires employee payload)
http://localhost:8080/api/nook/123456/registry/citizen/updateEmployee/5876390913
###
### Town URL examples
#### Create Town (Requires town payload)
http://localhost:8080/api/nook/123456/registry/town/create
#### Get Town
http://localhost:8080/api/nook/123456/registry/town/get
#### Delete Town
http://localhost:8080/api/nook/123456/registry/town/delete
#### Update Town (Requires town payload)
http://localhost:8080/api/nook/123456/registry/town/update

## Sample API Payloads
### Default Resident Payload

    {
        "name": "Frobert",
        "gender": "MALE",
        "species": "FROG",
        "birthday": "February 8th",
        "vacationHomeOwner": false,
        "citizenType": "RESIDENT",
        "personality": "JOCK",
        "favoriteSong": "MY_PLACE"
    }

### Default Employee Payload

    {
        "name": "Tom Nook",
        "gender": "MALE",
        "species": "TANUKI",
        "birthday": "May 30th",
        "vacationHomeOwner": true,
        "citizenType": "EMPLOYEE",
        "placeOfEmployment": "Town Hall",
        "jobDescription": "Handles home upgrades and assists incoming and outgoing residents"
    }

### Default Town Payload
    {
        "name": "Breezy Bay",
        "store": "NOOK_N_GO",
        "numberOfResidents": 10,
        "museum":{
            "artPieces":[ "Great Wave"],
            "bugs":[ "Elephant Beetle", "Stag Beetle", "Birdwing Butterfly", "Scorpion"],
            "fish":[ "Barred Knifejaw", "Piranha", "Sturgeon"],
            "fossils":[ "Ankylosaur", "T-Rex"]
    }



