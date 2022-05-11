# Delete Order

**URL** : `api/1.0/order/:pk/`

**URL Parameters** : `pk=[integer]` where `pk` is the ID of the order in the
database.

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : Admin

**Data** : `{}`

## Success Response

**Condition** : If the order exists.

**Code** : `204 NO CONTENT`

**Content** : `{}`

## Error Responses

**Condition** : If there was no order available to delete.

**Code** : `404 NOT FOUND`

**Content** :
```json
{
  "status": "NOT_FOUND",
  "timestamp": "2022-05-11T17:57:21.603000544",
  "message": "Order with id: 20 is not found in database."
}
```