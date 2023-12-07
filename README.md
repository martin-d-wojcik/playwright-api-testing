# Api testing
This is a test automation project using playwright and java. The tests are validating an expected behavior of the Guy Ritchie Movie spring boot app.


---
## Installation
Package by running the command from project root
```
mvn clean install
```


---
## Tests of actor endpoints
#### testGetAllActorsShouldReturnArray
Expected result: array of actor objects   
Expected response: 200   

#### testGetActorByIdShouldPass
Expected result: actor object   
Expected response: 200

#### testGetActorByNameShouldPass
Expected result: actor object   
Expected response: 200

#### testAddNewActorShouldPass
Expected result: new actor added   
Expected response: 201

#### testUpdateActorShouldPass
Expected result: actor updated   
Expected response: 200

#### testDeleteActorByIdShouldPass
Expected result: actor deleted   
Expected response: 200
---

## Tests of movie endpoints
#### testGetAllMoviesShouldReturnArray
Expected result: array of moive objects   
Expected response: 200

#### testGetMoviebByIdShouldPass
Expected result: movie object   
Expected response: 200

#### testAddNewMovieShouldPass
Expected result: new movie added   
Expected response: 201

#### testUpdateActorShouldPass
Expected result: actor updated   
Expected response: 200

#### testDeleteActorByIdShouldPass
Expected result: actor deleted   
Expected response: 200
---

