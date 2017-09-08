# quickstart
Discord bot template with Discord4J and Spring.

## Requirements:
Maven and Docker.

## TL;DR
### Build and deploy:
mvn clean package docker:build
docker run -d -p 8080:8080 -e CLIENT_TOKEN="YOUR_CLIENT_TOKEN" mow/quickstart-bot

### Invite bot:
https://discordapp.com/oauth2/authorize?client_id=YOUR_CLIENT_ID&scope=bot&permissions=0

### List containers:
docker container ls

### Stop container:
docker stop CONTAINER_ID