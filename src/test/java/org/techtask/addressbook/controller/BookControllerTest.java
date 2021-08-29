package org.techtask.addressbook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.techtask.addressbook.controller.dto.BookDTO;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class BookControllerTest {

    private static final String BASE_URL = "/book";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldBeAbleToGetAddressBook() {
        try {
            mockMvc.perform(
                            MockMvcRequestBuilders.get(BASE_URL + "/")
                    ).andExpect(status().isOk())
                    .andDo(result -> {
                        System.out.println(result.getResponse().getContentAsString());
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldBeAbleToCreateMultipleAddressBook() {
        try {
            BookDTO bookDTO = BookDTO.builder().name("newBook").build();
            mockMvc.perform(
                            MockMvcRequestBuilders.post(BASE_URL + "/")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsBytes(bookDTO))
                                    .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(notNullValue())))
                    .andDo(result -> {
                        System.out.println(result.getResponse().getContentAsString());
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}