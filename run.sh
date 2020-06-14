docker-compose rm -f -s master-diet-backend-application
docker-compose pull
docker-compose up --build -d
docker-compose up