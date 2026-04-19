This project is a backend system that handles background tasks (jobs) such as sending emails or processing data. Instead of processing requests synchronously, it uses Apache Kafka to enable asynchronous, producer-consumer based job processing, improving performance and scalability.

The system ensures reliability by implementing retry mechanisms and a dead letter queue (DLQ) to handle failures gracefully. It also uses Redis to track job status across different stages (PENDING, PROCESSING, SUCCESS, FAILED).

To handle real-world distributed challenges, the system incorporates idempotency to prevent duplicate job execution and ensures concurrency safety, allowing multiple consumers to process jobs in parallel without data inconsistencies.
