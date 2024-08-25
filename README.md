# SpringBoot-Practice
Banking Transaction API Task

Sample:
Post method:
http://localhost:8080/api/accounts/create
with body:
```
{
  "name": "CK",
  "initialBalance": 1000.0
}
```

Get method:
http://localhost:8080/api/accounts/1
Response:
```
{
    "id": 1,
    "name": "CK",
    "initialBalance": 1000.0
}
```

Post method:
http://localhost:8080/api/transactions/transfer
with body:
```
{
  "senderId": 2,
  "receiverId": 1,
  "amount": 1000.0
}
```

Get method:
http://localhost:8080/api/transactions/1
```
[
    {
        "senderId": 2,
        "receiverId": 1,
        "amount": 1000.0,
        "timestamp": "2024-08-21T21:48:09.690883"
    }
]
```
