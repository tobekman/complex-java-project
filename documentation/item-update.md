# Update Item

**URL** : `/api/1.0/items/`

**Method** : `PUT`

**Auth required** : YES

**Permissions required** : Admin

**Data example**
```json
{
  "id": 11,
  "name": "Stjärnskruvmejsel",
  "price": 1000000.9
}
```

## Success Responses

**Condition** : Update can be performed either fully or partially.

**Code** : `302 FOUND`

**Content example** : For the example above.

```json
{
  "id": 11,
  "name": "Stjärnskruvmejsel",
  "categories": [],
  "price": 1000000.9
}
```

## Error Response

**Condition** : Item not found

**Code** : `404 NOT FOUND`

**Content** :
```json
{
  "status": "NOT_FOUND",
  "timestamp": "2022-05-11T15:47:52.305697509",
  "message": "Item is not found in database."
}
```



