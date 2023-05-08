package com.misiak.events.web;

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
public class OnlineGameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource({
            "test-data/events/example_request.json, test-data/events/example_response.json",
            "test-data/events/example_request_100.json, test-data/events/example_response_100.json",
            "test-data/events/example_request_1000.json, test-data/events/example_response_1000.json",
    })
    void testCalculateOrder(String requestFile, String responseFile) throws Exception {
        String requestBody = Files.readString(Path.of(new ClassPathResource(requestFile).getURI()));
        String expectedResponse = Files.readString(Path.of(new ClassPathResource(responseFile).getURI()));

        mockMvc.perform(post("/onlinegame/calculate")
                        .with(csrf().asHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }
}


