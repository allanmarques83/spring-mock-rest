# spring-mock-rest
Produces a mock for REST controllers and REST clients in the Spring Boot framework

## Installation

In your pom.xml add the following dependency:

```bash
<dependency>
	<groupId>io.github.allanmarques83</groupId>
	<artifactId>spring-mock-rest</artifactId>
	<version>1.1.4</version>
</dependency>
```

## Usage:
1 . Apply the annotation @EnableRestMock in your SpringBoot application class. E.g:
```java
@SpringBootApplication
@EnableRestMock
public class MyApplication {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
```

2 . Add a .json file (fake json response body) in the resources path of spring framework.

3 . In your Rest controller or client class, add the annotation below in the desired method. E.g:

```java
@GetMapping("/test")
@MockRest(mockId="123456", response="your_file.json", statusCode=400)
public ResponseEntity<String> someMethod(@RequestParam(required=false) String mockId) {
    return ResponseEntity.ok("real response");
}
```
If you call the endpoint service: /test?mockId=123456 the response is gonna be your mock with the http status code 400.

## Multiple mock for the same REST operation:
```java
@MockRestValues({
    @MockValue(mockId="testFake1", response="mockrest/test_fake.json", statusCode=400),
    @MockValue(mockId="testFake2", response="mockrest/test_fake.json", statusCode=401)
})
public ResponseEntity<String> someMethod(@RequestParam(required=false) String mockId) {
    return ResponseEntity.ok("real response");
}
```
## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[GPL-3.0](https://choosealicense.com/licenses/mit/)
