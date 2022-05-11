# Show All Users

**URL** : `/api/1.0/users/`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Admin

## Success Response

**Code** : `200 OK`

**Content examples**

```json
[
  {
    "fullname": "Örgen Eriksson",
    "username": "user",
    "email": "user@hotmail.com",
    "address": "StenSaxPåsegatan 19 89942 Storstaden",
    "orders": []
  },
  {
    "fullname": "Lotta Lotto",
    "username": "admin",
    "email": "admin@hotmail.com",
    "address": "Norra pastejkökstorget 123 12334 Småstaden",
    "orders": []
  }
]
```

# Show Single User

**URL** : `/api/1.0/users/:pk/`

**URL Parameters** : `pk=[integer]` where `pk` is the ID of the User in the
database.

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Admin

## Success Response

**Condition** : If User exists.

**Code** : `302 FOUND`

**Content example**

```json
{
  "fullname": "Lotta Lotto",
  "username": "admin",
  "email": "admin@hotmail.com",
  "address": "Norra pastejkökstorget 123 12334 Småstaden",
  "orders": []
}
```

## Error Responses

**Condition** : If User does not exist with `id` of provided `pk` parameter.

**Code** : `404 NOT FOUND`

**Content** : 
```json
{
  "status": "NOT_FOUND",
  "timestamp": "2022-05-11T14:08:30.392461625",
  "message": "User with id: 523 is not found in database."
}
```



