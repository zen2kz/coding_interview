# System architecture 

https://medium.com/distributed-knowledge/scalable-web-architectures-concepts-design-6fd372ee4541

https://bytebytego.com/courses/system-design-interview/scale-from-zero-to-millions-of-users

![Typical scalable app](https://miro.medium.com/max/1200/1*7Vq-CAUby3nt0UkF4GKhUg.png)

![Typical AWS system](https://developerck.com/wp-content/uploads/2021/08/aws_moodle_horizontal-1024x499.png?x98144)

![Typical AWS system](https://static-com.jingtaikeji.com/media/technology/Technology-CloudComputing/4-5-technology-cloudcomputing-pic1.jpg)

https://cloudacademy.com/blog/architecting-on-aws-the-best-services-to-build-a-two-tier-application/

![Multi-DC app](https://www.simform.com/wp-content/uploads/2017/11/Multi-data-Centers-Architecture.png)

![GCP Web app](https://storage.googleapis.com/gweb-cloudblog-publish/images/7_ykbxUwk.0999056119991123.max-2000x2000.jpg)

- When a user sends a request to your website, Cloud DNS translates the hostname to your web server’s IP address. 
- Then the request goes to Cloud CDN, which responds from cache. If there is no cached response, then the request goes to Global Load Balancing which balances load across the web servers on Compute Engine. You can even set on-prem or another cloud as backends. 
- Static files such as images are served from Cloud Storage. Then the internal load balancer sends the request to the app servers and eventually to any databases. 
- Use the Firestore document database for user profiles and activities.
- Use CloudSQL for relational data.
- To protect your backend from layer 3 and 4 DDoS attacks, enable Cloud Armor with a global load balancer.
- This example uses Compute Engine as a backend, but you can also deploy your backend in containers running on GKE, Cloud Run or App Engine.
- To scale the infrastructure while using Compute Engine, use managed instance groups, which autoscale as load increases. (App Engine and Cloud Run scale with load automatically). 

More architectures for other types of apps: https://cloud.google.com/blog/products/application-development/13-popular-application-architectures-for-google-cloud
