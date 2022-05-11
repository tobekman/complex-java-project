# Delete Category

**URL** : `api/1.0/category/:pk/`

**URL Parameters** : `pk=[integer]` where `pk` is the ID of the category in the
database.

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : Admin

**Data** : `{}`

## Success Response

**Condition** : If the category exists.

**Code** : `204 NO CONTENT`

**Content** : `{}`

## Error Responses

**Condition** : If there was no category available to delete.

**Code** : `404 NOT FOUND`

**Content** :
```json
{
  "status": "NOT_FOUND",
  "timestamp": "2022-05-11T18:17:00.822696298",
  "message": "Category with id: 204 is not found in database."
}
```