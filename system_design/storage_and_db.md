# Databases and storage

### Common temrs

*CAP*
- *C*onsistency
- *A*vailabilty 
- *P*erformance

*ACID*
- *A*tomicy - transactions either fully succeed or fully fail
- *C*onsistency - DB state is always consistent
- *I*solation - concurent execution leaves DB in the same state as if they executed sequentially 
- *D*urability - once commited it cannot be lost


### Databases by CAP
| Category  | Description                            | Examples                                  | 
|-----------|----------------------------------------|-------------------------------------------|
| CP        | Risk of some data becoming unavailable | MongoDB, HBase, Memcache, BigTable, Redis |
| CA        | Network problems may stop the system   | MySQL, Oracle                             |
| CP        | Clients may read inconsistent data     | Cassandra, CouchDB, Redis                 |

| Database type   | On-premises example          | Cloud example                                                    |
|-----------------|------------------------------|------------------------------------------------------------------|
| Relational      | MySQL                        | Cloud SQL (GCP), Aurora (AWS)                                    |
| Key-Value       | Redis                        | Memorystore (GCP), ElastiCache (AWS)                             |
| Document        | MongoDB                      | MongoDB Atlas, DocumentDB (AWS), Datastore (GCP), DynamoDB (AWS) |
| Column-Oriented | Cassandra, ClickHouse, HBase | BigTable (GCP), BigQuery (GCP), Redshift (AWS)                   |
| Graph DB        | Neo4j, FlockDB               | Neptune (AWS), Aura (GCP)                                        |
| NewSQL          | CockroachDB, VoltDB          | Cloud Spanner (GCP)                                              |


*Use cases:* 
- Relational: financial data, sensitive data, reporting and search by arbitrary fields, ERP, CRM, e-commerce, less than 10 TB capacity
- Key-value: caching, session management, recommendations, games
- Document: prototyping and rapid development, user profiles, file metadata, mobile, IoT, 
- Column-oriented: high throughput applications, data warehouse, analytics, sparse data, monitoring and observability, logs, machine learning, predictions
- Graph DB: recomendations engines, hotel and flight booking services, social graph, master data management, compliance with GDPR and other regulations, AI, Digital asset management, fraud detection, semantic search 
- NewSQL: short-lived sessions, touches small amount of data, no table scans (only by indexes), globally consistent, gaming, supply chain and inventory management, large amount of data >10TB + transactional consistency

![Decision tree](https://d1.awsstatic.com/Startups/StartupPageAssets/how-to-choose-a-database-1.12737182f86dcb29938f211ad303d63ab7bdf29a.png)

** DB choice decision tree
![Decision tree](https://www.ml4devs.com/images/illustrations/sql-vs-nosql-cheatsheet.webp)


### Replication

Replication strategies:
- Master-slaves (or 1 write-only and many read-only databases) - most common strategy for SQL and NoSQL, if master fails a new master can be selected
- Multi-master
- Circular replication

### Scalability

RDBMS can be scaled vertically and horizontal scaling is limited, most of NoSQL can be scaled both vertically and horizontally

- Amazon RDS service allows to have a DB server with 24 TB of RAM
- Stackoverflow in 2013 had 10 million monthly unique visitors and only one master DB

Drawbacks of vertical scaling:
- there is a hardware limit of how much the service can be scaled
- greater risk of Single Point of Failure
- more expensive

### Sharding / Partitioning 

Sharding is a way of horizontal scaling DBs. It distributes data from one table between multiple servers by partition key (for example user_id)

Challenges of sharding:
1. Resharding data - needed when a single shard cannot handle the load or when data is distributed unevenly between shards
2. Celebrity problem (i.e. hot spot key) - may cause excessive use of one shard
3. As data is distributed between servers joining tables becomes challenging - the solution is denormalization so queries can be performed on the single table

Consistent hashing - is a strategy for dividing up keys/data between multiple machines by assigning them a position on an abstract circle, or hash ring.

## Cache

#### Cache types
- Read-through (or cache-aside) - cache is checked before reading from the DB and the data put in cache after reading from the DB
- Write-through - cache is written right after inserting data to the DB

### High-availability
- multiple cache servers accross different data centers
- overprovision required memory

### Eviction policy
Triggered when the cache is full
- LRU - Least-recently used - most popular - good for small caches
- LFU - least-frequently used - use case CDN, peak/hype resources 
- FIFO - first-in first-out
- Shortest TTL -
- Random 
- No eviction - new writes fail

