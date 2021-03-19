# Docker db command
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name game-service -e POSTGRES_USER=ahmed -e POSTGRES_PASSWORD=123456 -e POSTGRES_DB=game-service -p 5432:5432 postgres:10.5
# Project schema
![image](https://user-images.githubusercontent.com/56077375/111795450-8075b800-88c7-11eb-97ab-fa36b6b06229.png)

