# Authentication and user creation

## Create user

To create a new user that will get the role of "USER".

**URL** : `/api/1.0/auth/signup`

**Method** : `POST`

**Auth required** : NO

**Data example**

```json
{
  "username": "BigBoy",
  "password": "storpojke",
  "email": "bigboy@hotmail.com",
  "fullName": "Örgen Eriksson",
  "address": {
    "street": "StenPåseSax-gatan 19",
    "postalCode": "812345",
    "city": "Storstaden"
  }
}
```
## Error Response

**Condition** : If user email or username already exists in database.

**Code** : `403 FORBIDDEN`

**Content** :

```json
{
  "status": "FORBIDDEN",
  "timestamp": "2022-05-11T18:22:32.924799257",
  "message": "A user with this email and/or username already exists in database"
}
```

## Login

Used to collect a Token for a registered User.

**URL** : `/api/1.0/auth/login/`

**Method** : `POST`

**Auth required** : NO

**Data example**

```json
{
    "username": "MyNameIsBenny",
    "password": "abcd1234"
}
```


## Success Response

**Code** : `200 OK`

**Content example**

```json
{
  "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY1MjI3MTU0NiwiZXhwIjoxNjUyMjc1MTQ2fQ.QYIjhUo1vwmtfXXdgSyKzYr8_A7-Ysw_yYTGffsFyumV7CAmOIepkTpXfFDDlJqt4MPsnc5vbNmpK06nGk6w2g"
}
```

## Error Response

**Condition** : If 'username' and 'password' combination is wrong.

**Code** : `400 BAD REQUEST`

**Content** :

```json
{
  "timestamp": "2022-05-11T13:21:53.626+00:00",
  "status": 401,
  "error": "Unauthorized",
  "path": "/api/1.0/auth/login"
}
```

## Sample data created at startup for testing purposes

USER:
```json
{
    "username": "user",
    "password": "password"
}
```

ADMIN:
```json
{
    "username": "admin",
    "password": "password"
}
```
