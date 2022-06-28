package io.github.allanmarques83;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MockRestAspect {
    @Around("@annotation(MockRest)")
    public Object getMock(ProceedingJoinPoint joinPoint, MockRest MockRest) throws Throwable {
        if(joinPoint.getArgs() == null) return joinPoint.proceed();
        return createResponse(MockRest.response(), MockRest.statusCode());
    }

    @Around("@annotation(MockRestValues)")
    public Object getMocks(ProceedingJoinPoint joinPoint, MockRestValues MockRestValues) throws Throwable {
        if(joinPoint.getArgs() == null) return joinPoint.proceed();

        MockValue mockrest = null;

        for(Object arg : joinPoint.getArgs()) {
            mockrest = isMockIdPresent(MockRestValues, arg.toString());
            if(mockrest != null) break;
        }

        if(mockrest == null) return joinPoint.proceed();

        return createResponse(mockrest.response(), mockrest.statusCode());
    }

    private ResponseEntity<String> createResponse(String pathJson, int httpStatusCode) {
        String jsonContent = getJsonFile(pathJson);
        return ResponseEntity.status(httpStatusCode).contentType(MediaType.APPLICATION_JSON).body(jsonContent);
    }

    private String getJsonFile(String pathJson) {

        try {
            Resource resource = new ClassPathResource(pathJson);
            Path path = Paths.get(resource.getURI());
            byte[] bytes = Files.readAllBytes(path);
            return new String (bytes);
        } catch (IOException e) {
            System.out.printf(
                "Error on set spring-mock-rest. Trace: %s%n", e.getMessage()
            );
        }
        return null;
    }

    private MockValue isMockIdPresent(MockRestValues mocks, String arg) {
        return Arrays.stream(mocks.value()).filter(mock -> mock.mockId().equals(arg)).findFirst().orElse(null);
    }
}
