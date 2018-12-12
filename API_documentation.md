# APIs

## Path-Method Summary

### /users

| Method | Summary | Schema | Status Code |
|-------|:--------|--------|-----|
| GET    | Retrieved user profile | String | 201 |
| POST   | Create new user | String | 200 |
| DELETE | Delete a user | String | 204 |

### /users/me

| Method | Summary | Schema | Status Code |
|-------|:--------|--------|-----|
|GET| Retrieved User Profile| String | 201 |


### user/{userId} 
| Method | Summary | Schema | Status Code |
|-------|:--------|--------|--------|
| GET    | Retrieved user id | String | 201 |
| POST   | Create new user id | String | 200|
| DELETE | Delete a user id | String | 204 |

### /users/{userId}/progress
| Method | Summary | Schema | Status Code |
|-------|:--------|--------|-------|
| GET    | Retrieved user progress | String | 201|
| POST   | Create new user progress | String | 200 |
| DELETE | Delete a user progress | String | 204 |

 ### /users/{progressID}
 | Method | Summary | Schema | Status Code |
 |-------|:--------|--------|---------|
 | GET    | Retrieved user progress id | User | 201 |
 | DELETE | Delete a user progress id | User | 204 |

### /progress
| Method | Summary | Schema | Status Code |
|-------|:--------|--------|---------|
| GET    | Retrieved user progress id | User | 201 |
| POST   | Create new user progress id | User | 200 |
| DELETE | Delete a user progress id | User | 204 |

### /progress/{progressID}/all
| Method | Summary | Schema | Status Code |
|-------|:--------|--------|---------|
| GET    | Retrieved user progress id | User | 201 |
| POST   | Create new user progress id | User | 200 |
| DELETE | Delete a user progress id | User | 204 |

## Parameters

| Name         | Located In | Required | Description |
|--------------|:-----------|----------|-------------|              
| userRepository |  path    |    yes   |  unique identifier of User Controller |
| userService   |  path | yes | unique identifier of User Controller |


## Schemas


### User

| Property | Type | Summary |
|-------|:------|-------|
| userID  | String | UUID |
| created | Date | Date |
| username | String | User created name |
| oAuthId | String | Google Authorization|
| progress | String| User Progress |


### Progress 

| Property | Type | Summary |
|-------|:------|-------|
| progressId | String | UUID |
| updated | Date | Date |
|levels | String | List of levels |
| score | Integer | A number of points |
|user| String | Player of game |
