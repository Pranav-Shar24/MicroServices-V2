# MicroServices
 MicroServices Using Java, Springboot, Spring Cloud, and Docker

The following project was intented to create a micro-service architecture based project.

Technologies used:
Java
Spring Boot
Spring cloud

The compoenents:

Currency Exchange: This micro-service is used to convert <source currency> to <target currency>.
Uses Rest API, and internally uses an external API to that converts currency in the real time.
POJO is made, to accomadate the resultant send by external API. Rest Template is used.

Currency Conversion: Converts the currenct to the given quantity based on the input. Calls currency excahnge.

Naming server: Eureka server the register all the compoenents.

API Gateway: Is called to complete the request.

Used Docker, Rabbit MQ, Feing components of micro-service