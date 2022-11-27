# System components 

## Web-Application Firewall

Features
- protection against known vulnerabilities and threats (like CSRF, XSS and SQL Injections)
- identification and bot protection
- DDoS attack prevention
- Advanced control for IP blocking and geo blocking

Types
- Network-based - hardware - for low latency - expensive
- Host-based - integrated into an app - consumes local sever resources
- Cloud-based - low upfront cost - easy to configure - higher latencies - third-party

## CDN - Content-Delivery Network 

A network of geographically dispersed servers used to deliver static content. CDN servers cache static content like images, videos, CSS, JavaScript files, etc.

Workflow: 
1. User A requests an image from the CDN (like https://mysite.akamai.com/image-manager/img/logo.jpg)
2. CDN checks if it has this image in cache and if not queries it from the origin storage (for example S3)
3. The origin service returns an image and also an optional header with TTL
4. CDN cachesh the image 
5. User B request the same image and it is served directly from CDN (as long as TTL is not expired)


Considerations:
1. CDNs may be expensive as they are 3rd party services, so it is good idea to cache frequently used files only
2. Caching time should not be too short or too long
3. Handle CDN outage / fallback to requesting from origin
3. Invalidation before expiring - either through CDN API or using object versioning


## API Gateway / Reverse proxy / Load Balancer

Features of reverse proxy / load balancer: 
- hides internal system structure / routing / url rewrite
- distributes load between servers
- maintains high availability and scalability
- can have antivirus, packet filter to prevent attacks
- can have a caching layer
- can terminate SSL encryption

Load balancers are best suited for geo distributed deployments (resilence + redundancy)

Features of API Gateways (on top of what can be offered by reverse proxy / load balancer):
- Orchestration and aggregation of requests
- Protocol translation (for example from JSON to gRPC)
- Authentication and authorization
- IP Whitelisting
- Rate limiting, throttling, quota
- Retry policy and circuit breaker 
- Logging, tracing, correlation

API Gateways are best for microservices applications.

Load balancer is more cost-effective for low traffic / API Gateway is more cost-efficient for high traffic 


### Load balancing strategies
- *Round-robin* - simply send requests to servers in a circle - most common - predictable requests and equal processing capabilities of servers
- *Weighed round-robin* - similar to round robin, but for servers with different processing capabilities
- *Least connections* - sends requests to a server with least active connections - good for varied connection times / longer lived connections and equal processing capabilities of serviders
- *Weighed least connections* - similar to least connections, but for servers with different processing capabilities
- *Resource-based / adaptive* - status indicators / health checks of active serviers - good for vaired loads
- *SDN adaptive* - similar to previous but also checks network status. Needs SDN controller
- *Fixed weighing* - sends traffic to the server with highest weight until if fails and then to next server with highest weight. - good for cases when a single server can handle all load, but there is another "hot spare" server which can pick up load when the first fails
- *Weighed response time* - server weights are calculated based on their response time
- *Source IP Hash / Sticky Sessions* - allocates clients to a particular servier - good when we want a client to always be redirected to the same server
- *URL Hash* - simuilar to Source IP Hash method, but based on the url of the requests - when we want a requests to the same url to go to the same service 


## Computing 

### Kubernetes engine vs Serverless (i.e. lambdas)
For high traffic with no iddle Kubernetes engine is more efficient. For services with long iddle times serverless is more efficient

![GCP decision tree](https://www.sphereinc.com/wp-content/uploads/2021/03/Decision-tree-for-GCP-compute-services.webp)

## Data Centers

### Geo-DNS
GeoDNS resolves the name to ip address based on user location

### Scalability
It is good idea to have services stateless (i.e. the user session should be stored in some kind of DB (like Redis) and not in the service)

It is good idea to have the application hosted in at least 2 data centers / availability zones. It allows to choose the closest one to a user and also failover traffic when there are issues with one of DC

### Technical challengers of multi-data center setup
- Traffic redirection - need tools like geo DNS to efficiently redirect traffic
- Data Sync - data need to be replicated between DCs
- Test and deployment - testing should be done in different locations and automated if possible

## Message Queue / Event bus

Used for async tasks. Allows to scale publishers and consumers independently.
More efficient use of resources as the queue can grow during peak usage and then gradually processed when usage is lower
Good for things like video tanscoding, image resizing and editing and etc


## Logs, Obeservability, Metrics, Automation

It is good idea to aggregate logs in a centralized storage which also support viewing, searching and alerting (Example Google Cloud Logging)

It is also important to collect and monitor metrics like:
- Host level metrics: CPU, Memory, Disk, I/O
- Aggregated metrics like performance of the DB, caching tier
- Key business metrics like DAU (daily active users), retention, revenue and etc