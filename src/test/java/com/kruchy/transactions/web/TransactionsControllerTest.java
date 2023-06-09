package com.kruchy.transactions.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionsControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @CsvSource({
            "test-data/transactions/example_request.json, test-data/transactions/example_response.json",
            "test-data/transactions/request_100000_transactions.json, test-data/transactions/response_100000_transactions.json"
    })
    void testCalculateOrder(String requestFile, String responseFile) throws Exception {
        String requestBody = Files.readString(Path.of(new ClassPathResource(requestFile).getURI()));
        String expectedResponse = Files.readString(Path.of(new ClassPathResource(responseFile).getURI()));

        mockMvc.perform(post("/transactions/report")
                        .with(csrf().asHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }
}

