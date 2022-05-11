# Show All Items

**URL** : `/api/1.0/items/`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : User

## Success Response

**Code** : `200 OK`

**Content example**

```json
[
  {
    "id": 10,
    "name": "Hammare",
    "categories": [
      "Verktyg"
    ],
    "price": 39.9
  },
  {
    "id": 11,
    "name": "Stjärnskruvmejsel",
    "categories": [
      "Verktyg"
    ],
    "price": 99.9
  },
  {
    "id": 12,
    "name": "Japansk dragsåg",
    "categories": [
      "Verktyg"
    ],
    "price": 299.0
  },
  {
    "id": 13,
    "name": "Hörnsoffa",
    "categories": [
      "Möbler"
    ],
    "price": 17995.0
  }
]
```

# Show Single Item

**URL** : `/api/1.0/items/:pk/`

**URL Parameters** : `pk=[integer]` where `pk` is the ID of the Item in the
database.

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Admin

## Success Response

**Condition** : If Item exists.

**Code** : `302 FOUND`

**Content example**

```json
{
  "id": 10,
  "name": "Hammare",
  "categories": [
    "Verktyg"
  ],
  "price": 39.9
}
```

## Error Responses

**Condition** : If Item does not exist with `id` of provided `pk` parameter.

**Code** : `404 NOT FOUND`

**Content** :
```json
{
  "status": "NOT_FOUND",
  "timestamp": "2022-05-11T15:11:39.18660717",
  "message": "Item with id: 1023 is not found in database."
}
```



