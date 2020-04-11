### Run

##### Using only Dockerfile:
1. docker build -t master-diet-backend-application .
2. docker run --rm -it -p 8081:8081 master-diet-backend-application:latest
3. http://localhost:8081/actuator/health - should work