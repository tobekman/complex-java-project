# Show All Orders

**URL** : `/api/1.0/order/`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Admin

## Success Response

**Code** : `200 OK`

**Content example**

```json
[
  {
    "id": 20,
    "orderPrice": 10872.8,
    "createdAt": "2022-05-11",
    "userId": 5,
    "orderItems": [
      10,
      10,
      14,
      19,
      12
    ]
  },
  {
    "id": 22,
    "orderPrice": 10872.8,
    "createdAt": "2022-05-11",
    "userId": 5,
    "orderItems": [
      10,
      10,
      14,
      19,
      12
    ]
  },
  {
    "id": 24,
    "orderPrice": 10872.8,
    "createdAt": "2022-05-11",
    "userId": 5,
    "orderItems": [
      10,
      10,
      14,
      19,
      12
    ]
  }
]
```

# Show Single Order

**URL** : `/api/1.0/order/:pk/`

**URL Parameters** : `pk=[integer]` where `pk` is the ID of the Order in the
database.

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Admin

## Success Response

**Condition** : If Order exists.

**Code** : `302 FOUND`

**Content example**

```json
{
  "id": 20,
  "orderPrice": 10872.8,
  "createdAt": "2022-05-11",
  "userId": 5,
  "orderItems": [
    10,
    10,
    14,
    19,
    12
  ]
}
```

## Error Responses

**Condition** : If Order does not exist with `id` of provided `pk` parameter.

**Code** : `404 NOT FOUND`

**Content** :
```json
{
  "status": "NOT_FOUND",
  "timestamp": "2022-05-11T17:53:39.927971844",
  "message": "Order with id: 202 is not found in database."
}
```



