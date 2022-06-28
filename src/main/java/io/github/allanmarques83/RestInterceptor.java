package io.github.allanmarques83;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        MockRest mockRest = null;

        try {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            String mockId = (String)request.getParameter("mockId");
            if(mockId == null) return true;

            MockRestValues mockRestValues = handlerMethod.getMethodAnnotation(MockRestValues.class);

            if(mockRestValues != null) mockRest = getMockRest(mockRestValues, mockId);

            if(mockRest == null) mockRest = handlerMethod.getMethodAnnotation(MockRest.class);

            if(mockRest == null) return true;
            if(mockRest.mockId() == null) return true;
            if(!mockRest.mockId().equals(mockId)) return true;
            
            String jsonMock = getJsonFile(mockRest.response());

            PrintWriter out = response.getWriter();
            response.setStatus(mockRest.statusCode());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(jsonMock);
            out.flush();
            return false;
        } catch(Exception e) {
            System.out.printf(
                "Error on set spring-mock-rest. Trace: %s%n", e.getMessage()
            );
            return true;
        }
    }

    private MockRest getMockRest(MockRestValues mocks, String arg) {
        for(MockRest mock : mocks.value()) {
            if(mock.mockId().equals(arg)) return mock;
        }
        return null;
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
}

