# Netflix Ribbon and Hystrix playground

This is a project with learning tests for Netflix Ribbon and Hystrix configuration.

Ribbon is a client-side load balancer that gives you a lot of control over the behavior of HTTP and TCP clients.

The Ribbon property to play with:
```
# Max number of retries on the same server (excluding the first try)
 sample-client.ribbon.MaxAutoRetries=1
 
 # Max number of next servers to retry (excluding the first server)
 sample-client.ribbon.MaxAutoRetriesNextServer=1
 
 # Whether all operations can be retried for this client
 sample-client.ribbon.OkToRetryOnAllOperations=true
 
 # Interval to refresh the server list from the source
 sample-client.ribbon.ServerListRefreshInterval=2000
 
 # Connect timeout used by Apache HttpClient
 sample-client.ribbon.ConnectTimeout=3000
 
 # Read timeout used by Apache HttpClient
 sample-client.ribbon.ReadTimeout=3000
 
 # Initial list of servers, can be changed via Archaius dynamic property at runtime
 sample-client.ribbon.listOfServers=www.microsoft.com:80,www.yahoo.com:80,www.google.com:80
```

The Hystrix framework library helps to control the interaction between services by providing fault tolerance and latency tolerance. 
It improves overall resilience of the system by isolating the failing services and stopping the cascading effect of failures.

## The project structure

* `application` module contains Spring Boot Application;
* `logic` module contains the Dice Controller that has a single endpoint to emulate throwing the dice action.
The controller uses the Dice Service that, in turn uses fake REST api (via Feign client) to generate random number from 1 to 6;
* `functional-tests` module contains learning tests that show how to configure Ribbon retries and timeouts.

## The project dependencies
* Java 11.0.9
* Maven 3.8.1

## How to run the tests
* Clone the project
* Run `mvn clean verify`

## Useful links

* [Client Side Load Balancer: Ribbon] [1] (cloud.spring.io)
* [Retrying Failed Requests: Ribbon] [2] (cloud.spring.io)
* [Rest Client with Netflix Ribbon] [3] (baeldung.com)
* [Retrying Failed Requests with Spring Cloud Netflix Ribbon] [4] (baeldung.com)

[1]: https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-ribbon.html
[2]: https://cloud.spring.io/spring-cloud-netflix/multi/multi_retrying-failed-requests.html
[3]: https://www.baeldung.com/spring-cloud-rest-client-with-netflix-ribbon
[4]: https://www.baeldung.com/spring-cloud-netflix-ribbon-retry