# Cloud-native Microservices with Quarkus

Companion repository for the [Cloud-native Microservices with Quarkus](https://www.udemy.com/course/quarkus-by-example)
Udemy course.

## MongoDB

Create the collection via

```sh
docker compose exec -it mongo bash
# Inside the container
mongosh -u root

use logins
db.createCollection('logins-timed', {'timeseries': {'timeField': 'expiresAt'}, expireAfterSeconds: 10})
```
