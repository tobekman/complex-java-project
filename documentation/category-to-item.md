## Add Category to Item

**URL** : `api/1.0/category/add?categoryId=:cpk&itemId=:ipk`

**Query Parameters** : 

`cpk=[integer]` where `cpk` is the ID of the category in the
database.

`ipk=[integer]` where `ipk` is the ID of the item in the
database.

**Method** : `PUT`

**Auth required** : Admin

## Success Response

**Code** : `200 OK`

**Content example**

```json
{
  "id": 10,
  "name": "Hammare",
  "categories": [
    "Verktyg",
    "Maskiner"
  ],
  "price": 39.9
}
```

## Error Responses

**Condition** : If there was no User available to delete.

**Code** : `404 NOT FOUND`

**Content** :
```json
{
  "status": "NOT_FOUND",
  "timestamp": "2022-05-11T18:54:52.854193543",
  "message": "No categories or items found"
}
```