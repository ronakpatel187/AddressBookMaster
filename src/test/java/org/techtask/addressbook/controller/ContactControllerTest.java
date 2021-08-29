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
import org.techtask.addressbook.controller.dto.ContactDTO;
import org.techtask.addressbook.service.BookService;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class ContactControllerTest {

    private static final String BASE_URL = "/book";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BookService bookService;

    @Test
    void shouldBeAbleToGetAllContacts() {
        try {
            mockMvc.perform(
                            MockMvcRequestBuilders.get(BASE_URL + "/contact")
                    ).andExpect(status().isOk())
                    .andDo(result -> {
                        System.out.println(result.getResponse().getContentAsString());
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldBeAbleToPrintAllRecordInAnAddressBook() {
        try {
            mockMvc.perform(
                            MockMvcRequestBuilders.get(BASE_URL + "/contact?bookId=1111")
                    ).andExpect(status().isOk())
                    .andDo(result -> {
                        System.out.println(result.getResponse().getContentAsString());
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldBeAbleToAddNewContact() {

        try {
            BookDTO bookDTO = BookDTO.builder().id(1111L).build();
            ContactDTO contactDTO = ContactDTO.builder().firstName("Jinal").lastName("Patel").phoneNumber("0450223565").book(bookDTO).build();
            mockMvc.perform(
                            MockMvcRequestBuilders.post(BASE_URL + "/contact")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsBytes(contactDTO))
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

    @Test
    void shouldBeAbleToDeleteExistingContact() {
        try {
            String contactToDelete = "1";
            mockMvc.perform(
                    MockMvcRequestBuilders.delete(BASE_URL + "/contact/" + contactToDelete)
                            .accept(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk());

            mockMvc.perform(
                            MockMvcRequestBuilders.get(BASE_URL + "/contact/" + contactToDelete)
                                    .accept(MediaType.APPLICATION_JSON)).andExpect(status().is(404))
                    .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldBeAbleToGetUniqueAllContacts() {
        try {
            mockMvc.perform(
                            MockMvcRequestBuilders.get(BASE_URL + "/contact/unique")
                    ).andExpect(status().isOk())
                    .andDo(result -> {
                        System.out.println(result.getResponse().getContentAsString());
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}