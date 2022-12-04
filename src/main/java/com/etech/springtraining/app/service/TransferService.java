package com.etech.springtraining.app.service;

import com.etech.springtraining.app.converter.CustomerMapper;
import com.etech.springtraining.app.dto.CustomerSaveRequestDto;
import com.etech.springtraining.app.entity.Customer;
import com.etech.springtraining.app.exception.NotFoundException;
import com.etech.springtraining.app.gen.Constants;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import static com.etech.springtraining.app.gen.Constants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferService {

    private final WebClient webClient;

    public Mono<RuntimeException> handle4xxErrorResponse(ClientResponse clientResponse) {
        Mono<String> errorResponse = clientResponse.bodyToMono(String.class);
        return errorResponse.flatMap((message) -> {
            log.error("ErrorResponse Code is " + clientResponse.rawStatusCode() + " and the exception message is : " + message);
            throw new NotFoundException(message);
        });
    }
    public Customer save(CustomerSaveRequestDto customerSaveRequestDto) throws JSONException {

        Customer customer = CustomerMapper.INSTANCE.covertToCustomer(customerSaveRequestDto);

        try {
        return webClient.post().uri(ADD_CUSTOMER)
                .body(Mono.just(customer), Customer.class)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> handle4xxErrorResponse(clientResponse))
                .bodyToMono(Customer.class)
                .block();
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            JSONObject errorResponse = new JSONObject(ex.getResponseBodyAsString());
            log.error("Hata mesaji ->" + errorResponse.getString("message"));
            log.error("WebClientResponseException in addNewEmployee", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in addNewEmployee ", ex);
            throw ex;
        }
    }
}
