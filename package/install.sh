rm -rf ../volums/mysql-volum/data
mkdir ../volums/mysql-volum/data
rm -rf ../volums/redis-volum/data
mkdir ../volums/redis-volum/data
docker stop $(docker ps -aq) && docker rm $(docker ps -aq)
docker rmi $(docker images -aq)
docker compose -f docker-compose.yaml -p package up -d
