# FixerGateway

## Start project
docker compose --env-file ./app.env up -d

## Stop project
docker compose --env-file ./app.env down --rmi local

## Postman test requests collection 
Test Post requests can be found in root folder/FixerIO_Gateway.postman_collection.json