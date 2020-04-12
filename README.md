<img src="https://img.shields.io/github/languages/code-size/master-diet/master-diet-backend?style=for-the-badge"> <img src="https://img.shields.io/github/repo-size/master-diet/master-diet-backend?color=purple&style=for-the-badge"> 
<img src="https://img.shields.io/github/languages/count/master-diet/master-diet-backend?color=green&style=for-the-badge"> 
<img src="https://img.shields.io/github/languages/top/master-diet/master-diet-backend?color=orange&style=for-the-badge">
<img src="https://img.shields.io/github/last-commit/master-diet/master-diet-backend?color=darkgreen&style=for-the-badge">
<img src="https://img.shields.io/github/issues/master-diet/master-diet-backend?&style=for-the-badge">
<img src="https://img.shields.io/github/issues-pr/master-diet/master-diet-backend?style=for-the-badge">

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

