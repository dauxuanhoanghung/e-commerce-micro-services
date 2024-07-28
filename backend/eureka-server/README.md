# Eureka server

## Install and run

```bash Using docker only
docker build -t eureka-server .
docker run -p 8761:8761 eureka-server
```

```bash Using docker compose
docker compose -f compose.dev.yml up --build
```
