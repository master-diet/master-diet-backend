### Run

##### Using only Dockerfile (only backend):
```bash
docker build -t master-diet-backend-application .
docker run --rm -it -p 8081:8081 master-diet-backend-application:latest
```

##### Using docker-compose.yml
```
docker-compose up --build
```

##### Using run.sh [RECOMMENDED] - both on Windows and Linux (Consider usage of GitBash on Windows)
```
./run.sh
```

No matter which build method you chose 
```
curl http://localhost:8081/actuator/health 
```
SHOULD WORK.

