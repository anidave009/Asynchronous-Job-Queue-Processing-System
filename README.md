# Asynchronous-Job-Queue-Processing-System
This project is a backend system that handles background tasks (jobs) like sending emails or processing data. Instead of doing everything immediately, it uses Apache Kafka to process tasks asynchronously, making the system faster and more scalable. It also handles failures using retries and a dead letter queue, and tracks job status using Redis.
