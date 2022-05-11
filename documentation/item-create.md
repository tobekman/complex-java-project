## Create item

**URL** : `/api/1.0/items/`

**Method** : `POST`

**Auth required** : Admin

**Data example**

```json
{
  "name": "Fallskärm",
  "price": 1814
}
```

## Success Response

**Code** : `201 CREATED`

**Content example**

```json
{
  "id": 20,
  "name": "Fallskärm",
  "categories": [],
  "price": 1814.0
}
```