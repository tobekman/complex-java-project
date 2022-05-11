# E-commerce REST-API

## Run with Docker
https://github.com/tobekman/complex-java-project

https://github.com/tobekman/complex-java-project-jmsreceiver

Make sure both projects are in the same folder. 

Run `docker compose up` in the root folder of the complex-java-project.


## Open Endpoints

Open endpoints require no Authentication.

* [Auth](documentation/auth.md) : `POST /api/1.0/auth/`

## Endpoints that require Authentication

Closed endpoints require a valid Token to be included in the header of the
request. A Token can be acquired from the Login view above.

### User related

Require admin role:
* [Get all users](documentation/user-get.md) : `GET /api/1.0/users/`
* [Get single user](documentation/user-get.md) : `GET /api/1.0/users/`
* [Delete user](documentation/user-delete.md) : `DELETE /api/1.0/users/`
* [Update user](documentation/user-update.md) : `PUT /api/1.0/users/`

### Item related

Require user role:
* [Get all items](documentation/item-get.md) : `GET /api/1.0/items/`
* [Get single item](documentation/item-get.md) : `GET /api/1.0/items/`

Require admin role:
* [Create item](documentation/item-create.md) : `POST /api/1.0/items/`
* [Delete item](documentation/item-delete.md) : `DELETE /api/1.0/items/`
* [Update item](documentation/item-update.md) : `PUT /api/1.0/items/`

### Order related

Require user role:
* [Create order](documentation/order-create.md) : `POST /api/1.0/order/`

Require admin role:
* [Get all orders](documentation/order-get.md) : `GET /api/1.0/order/`
* [Delete order](documentation/order-delete.md) : `DELETE /api/1.0/order/`

### Category related

Require user role:
* [Get all categories](documentation/category-get.md) : `GET /api/1.0/category/`

Require admin role:
* [Create category](documentation/category-create.md) : `POST /api/1.0/category/`
* [Delete category](documentation/category-delete.md) : `DELETE /api/1.0/category/`
* [Add category to item](documentation/category-to-item.md) : `PUT /api/1.0/category/add`

## JMS - Receiver service
Simple jms receiver that receives order message and create a text file with information about the order.

**Content example**

```
ORDER: ca488fef-adf7-40a6-840a-70a5ef5862ac
Date: 2022-05-11T21:03:55

Address: 
	Lotta Lotto
	Norra pastejkökstorget 123
	12334
	Småstaden

ITEMS: 
	Hammare - 39.9
	Hammare - 39.9
	Bord - 6995.0
	Gräsklippare - 3499.0
	Japansk dragsåg - 299.0

Total price: 10872.8
```
