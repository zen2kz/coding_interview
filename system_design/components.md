# Common components

## API Gateway / Reverse proxy / Load Balancer



## Databases

### Common tems 

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


