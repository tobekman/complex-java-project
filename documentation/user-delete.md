# Delete User

**URL** : `api/1.0/users/:pk/`

**URL Parameters** : `pk=[integer]` where `pk` is the ID of the user in the
database.

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : Admin

**Data** : `{}`

## Success Response

**Condition** : If the User exists.

**Code** : `204 NO CONTENT`

**Content** : `{}`

## Error Responses

**Condition** : If there was no User available to delete.

**Code** : `404 NOT FOUND`

**Content** : 
```json
{
  "status": "NOT_FOUND",
  "timestamp": "2022-05-11T14:15:34.799311127",
  "message": "User with id: 20 is not found in database."
}
```