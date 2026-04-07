#  Realtime Tracking Service

##  Description
Microservice permettant de suivre les bus en temps réel.

##  Technologies
- Spring Boot
- Kafka
- Redis
- WebSocket

##  Architecture
Kafka → Consumer → Service → Redis → WebSocket / REST

##  Run
```bash
mvn spring-boot:run
