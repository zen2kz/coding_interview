# Back-of-Envelope Estimation

### Latency numbers

- Memory is fast but the disk is slow. (reading 1 MB: memory - 3µs, SSD - 49µs, Disk - 825µs)
- Avoid disk seeks if possible (2ms)
- Simple compression algorithms are fast. (1KB - 2µs)
- Compress data before sending it over the internet if possible.
- Data centers are usually in different regions, and it takes time to send data between them (California to Netherlandes - 150ms)

### Cost of operaitons
- Read sequentially from HDD: 30 MB/s
- Read sequentially from SSD: 1 GB/s
- Read sequentially from memory: 4 GB/s
- Read sequentially from 1Gbps Ethernet: 100MB/s
- Cross continental network: 6–7 world-wide round trips per second.

### Availabliliy numbers

| Availablility | Per week | Per month | Per year |
|---------------|----------|-----------|----------|
| 99%           | 1.68 h   | 7.20 h    | 3.65 d   |
| 99.9%         | 10.1 m   | 43.8 m    | 8.76 h   |
| 99.95%        | 5.04 m   | 21.56 m   | 4.38 h   |
| 99.99%        | 1.01 m   | 4.32 m    | 52.56 m  |
| 99.999%       | 6.05 s   | 25.9 s    | 5.26 m   |


### Base numbers (i.e. what to ask)

- Monthly active users (MAU) or daily active users (DAU)
- How long to store data
- Number of writes per day
- Number of reads per day

### Average numbers 

- id - 64 byte
- UUID/GUID: 16B
- data row - 10 KB
- single char = 2 bytes.
- long or double = 8 bytes.
- coordinates = 8 bytes
- file / web page = 100 KB
- average resolution image = 300 KB
- good resolution image= 3 MB
- short Posted Video: 2MB
- maximum URL Size: ~2000 (depends on browser)
- standard videos for streaming = 100 MB per minute of video
- read to write ratio (if not given) 10:1 or 100:1

### Shortcuts 
- X million of users/requests * Y KB of data = X*Y GB of data (for example per day) 
- X million of users/requests * Y MB of data = X*Y TB of data
- 12 req/s * 100M per day

### What to calculate

- QPS (query-per-second)  
  - writes: DAU * (number of data units per day per user) / 24 / 3600   (24*3600 = 86400 i.e. 100000 if rounded) 
  - reads: writes * read ratio
    | Shortcut: 12 req/s * 100M per day
- peak QPS - 2 * QPS
- storage 
  - per day:  DAU * data unit size * (number of data units per day per user) * size  * 1.7 (empty space buffer)
  - per year / 5 years: storage per day * 365 * number of years of storage 
  | data <10 TB can fit RDBMS, if more - need an alternative solution
  | Shortcut: 1 Million Requests per day with 1 kb side is 1 GB per day
- cache
  - read requests per day * data unit size * 20% or 30% (cache to storage ratio)
- bandwidth - read requests * data unit size  / (24*3600)  * (1.1 or 1.01 - to include writes which are 1/10 or 1/100 of reads)
- number of servers
  - max simultaneous uses:  (number of CPU cores / Average Page Response Time in seconds) * 60 * User Click Frequency in seconds
  - max number of page requests per second: Number of CPU cores / Average time for a page request (in seconds) 
    | Example
    | Average PHP request time: 650ms
    | CPU cores: 2
    | Click frequency: 45 seconds (normal for eCommerce)
    | 2 cores / 0.65 = 3 page views per second * 60 * 0.75 = 135 Max simultaneous users.
  - Tomcat/jetty server running Spring boot application at a minimum will have 100 worker thread (200 default) to handle HTTP requests
    - so for 1000 requests per second with 100 threads I would use 10 + 20% more = 12 servers. 
  - Linux default limit in `/etc/security/limits.conf` is 1024 concurrenct connections which can be increased to 65535


## Reference numbers

### Users
- Facebook: 2.27B | YouTube: 2B | Instagram: 1B
- Pinterest: 332M | Twitter: 330M | Onedrive: 250M
- TikTok: 3.7M

### Visits
- Facebook: 26.12B | Twitter: 6.34B | Pinterest: 1.32B
- Spotify: 293M | Ikea: 233M | Nike: 110M
- Argos: 54M | John Lewis: 37M |Superdry: 3.5M
- Virgin Money: 1.8M | Aviva: 1.61M

### Systems

*SQL Database*
- storage: 60TB
- connections: 30K
- requests: 25K per second

*Cache (Redis)*
- storage: 300GB
- connections: 10K
- requests: 100K per second

*Web-Server*
- requests: 5-10K per second

*Queues/Streams*
- requests: 1000-3000 req/sec
- throughput (write): 1-50 MB/s
- throughput (read): 2-100 MB/s

https://deepu.tech/concurrency-in-modern-languages-final/