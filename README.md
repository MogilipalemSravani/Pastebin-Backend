## Persistence Layer

This application uses PostgreSQL as its persistence layer.

### Why PostgreSQL?
- Data persists across application restarts
- Supports concurrent requests reliably
- Suitable for automated testing against deployed environments
- Widely used in production-grade Spring Boot applications

The backend uses Spring Data JPA with PostgreSQL to store paste content,
expiry timestamps, and view counts. This ensures correct behavior even
when deployed on cloud or serverless platforms.
