# Update user

**URL** : `/api/1.0/users/`

**Method** : `PUT`

**Auth required** : YES

**Permissions required** : Admin

**Data example**
```json
{
  "id": 22,
  "username": "Daniel",
  "password": "nytt lösenord",
  "email": "danielsnyaemail@hotmail.com",
  "address": {
    "id": 23,
    "street": "StenPåseSax gatan 19",
    "postalCode": "812345",
    "city": "Storstaden"
  },
  "fullName": "Daniel Eriksson"
}
```

## Success Responses

**Condition** : Update can be performed either fully or partially.

**Code** : `200 OK`

**Content example** : For the example above.

```json
{
  "fullname": "Daniel Eriksson",
  "username": "Daniel",
  "email": "danielsnyaemail@hotmail.com",
  "address": "StenPåseSax gatan 19 812345 Storstaden",
  "orders": null
}
```

## Error Response

**Condition** : User not found

**Code** : `404 NOT FOUND`

**Content** : 
```json
{
  "status": "NOT_FOUND",
  "timestamp": "2022-05-11T14:24:23.559243219",
  "message": "User with id does not exist - please create new user"
}
```



