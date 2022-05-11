# Show All Categories

**URL** : `/api/1.0/category/`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : User

## Success Response

**Code** : `200 OK`

**Content example**

```json
[
  {
    "id": 7,
    "name": "Verktyg"
  },
  {
    "id": 8,
    "name": "Möbler"
  },
  {
    "id": 9,
    "name": "Maskiner"
  }
]
```

# Show Single Category

**URL** : `/api/1.0/category/:pk/`

**URL Parameters** : `pk=[integer]` where `pk` is the ID of the category in the
database.

**Method** : `GET`

**Auth required** : YES

**Permissions required** : User

## Success Response

**Condition** : If Category exists.

**Code** : `302 FOUND`

**Content example**

```json
{
  "id": 7,
  "name": "Verktyg"
}
```

## Error Responses

**Condition** : If category does not exist with `id` of provided `pk` parameter.

**Code** : `404 NOT FOUND`

**Content** :
```json
{
  "status": "NOT_FOUND",
  "timestamp": "2022-05-11T18:08:01.01354309",
  "message": "Category with id: 74 is not found in database."
}
```



