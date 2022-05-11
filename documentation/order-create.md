## Create order

**URL** : `api/1.0/order?userId=:pk`

**Query Parameters** : `pk=[integer]` where `pk` is the ID of the item in the
database.

**Method** : `POST`

**Auth required** : Admin

**Data example**

```json
[
  {
    "id": 10,
    "name": "Hammare",
    "price": 39.9
  },
  {
    "id": 10,
    "name": "Hammare",
    "price": 39.9
  },
  {
    "id": 14,
    "name": "Bord",
    "price": 6995
  },
  {
    "id": 19,
    "name": "Gräsklippare",
    "price": 3499
  },
  {
    "id": 12,
    "name": "Japansk dragsåg",
    "price": 299
  }
] 
```

## Success Response

**Code** : `201 CREATED`

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