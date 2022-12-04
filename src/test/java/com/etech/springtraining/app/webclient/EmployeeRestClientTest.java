package com.etech.springtraining.app.webclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeRestClientTest {

    public static MockWebServer mockWebServer;

    @Mock
    private WebClient webClientMock;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersMock;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriMock;
    @Mock
    private WebClient.RequestBodySpec requestBodyMock;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriMock;
    @Mock
    private WebClient.ResponseSpec responseMock;

    private final ObjectMapper mapper = new ObjectMapper();

    @InjectMocks
    EmployeeRestClient employeeRestClient;


    @BeforeAll
    static void beforeAll() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }
    @AfterAll
    static void afterAll() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void setUp() {
        //String baseUrl = String.format("http://localhost:%s/employeeservice",mockWebServer.getPort());
        employeeRestClient = new EmployeeRestClient(webClientMock);
    }

    @Test
    void addNewEmployee() {
        Employee employee = new Employee(1L, "sema", "toltar", 24, "female", "senior");
        Employee webClientResponse = new Employee(1L, "sema", "toltar", 24, "female", "senior");

        when(webClientMock.post()).thenReturn(requestBodyUriMock);
        when(requestBodyUriMock.uri("/v1/employee")).thenReturn(requestBodyMock);
        when(requestBodyMock.syncBody(employee)).thenReturn(requestHeadersMock);
        when(requestHeadersMock.retrieve()).thenReturn(responseMock);
        when(responseMock.bodyToMono(Employee.class)).thenReturn(Mono.just(webClientResponse));

        Employee response = employeeRestClient.addNewEmployee(employee);

        assertEquals(response.getFirstName(), webClientResponse.getFirstName());
    }

    @Test
    void retrieveEmployeeById() throws JsonProcessingException, InterruptedException {
        Employee employee = new Employee(1L, "sema", "toltar", 24, "female", "senior");

        mockWebServer.enqueue(new MockResponse().setBody(mapper.writeValueAsString(employee))
                .addHeader("Content-Type", "application/json"));



        Employee response = employeeRestClient.retrieveEmployeeById(1L);

        assertEquals("sema", response.getFirstName());
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/employeeservice/v1/employee/1", recordedRequest.getPath());
        System.err.println(recordedRequest.getPath());
        System.err.println(recordedRequest.getMethod());
    }

    @Test
    void retrieveEmployeeByIdMock() throws JsonProcessingException, InterruptedException {

        Long employeeId = 100L;
        Employee employee = new Employee(employeeId, "sema", "toltar", 24, "female", "senior");
        when(webClientMock.get()).thenReturn(requestHeadersUriMock);
        when(requestHeadersUriMock.uri("/v1/employee/{id}", employeeId)).thenReturn(requestHeadersMock);
        when(requestHeadersMock.retrieve()).thenReturn(responseMock);
        when(responseMock.bodyToMono(Employee.class)).thenReturn(Mono.just(employee));

        Employee response = employeeRestClient.retrieveEmployeeById(100L);

        System.err.println(response.toString());
        assertEquals(employee.getFirstName(), response.getFirstName());
    }

}