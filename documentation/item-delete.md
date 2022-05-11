# Delete Item

**URL** : `api/1.0/items/:pk/`

**URL Parameters** : `pk=[integer]` where `pk` is the ID of the item in the
database.

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : Admin

**Data** : `{}`

## Success Response

**Condition** : If the Item exists.

**Code** : `204 NO CONTENT`

**Content** : `{}`

## Error Responses

**Condition** : If there was no Item available to delete.

**Code** : `404 NOT FOUND`

**Content** :
```json
{
  "status": "NOT_FOUND",
  "timestamp": "2022-05-11T15:42:58.739382429",
  "message": "Item with id: 1022 is not found in database."
}
```