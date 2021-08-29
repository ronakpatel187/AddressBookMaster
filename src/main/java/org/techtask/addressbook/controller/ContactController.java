package org.techtask.addressbook.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.techtask.addressbook.controller.dto.ContactDTO;
import org.techtask.addressbook.model.Contact;
import org.techtask.addressbook.service.BookService;
import org.techtask.addressbook.service.ContactService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class ContactController {

    @Autowired
    ContactService contactService;

    @Autowired
    BookService bookService;

    @Autowired
    protected ModelMapper modelMapper;

    @GetMapping(value = "/contact")
    public List<ContactDTO> get(@RequestParam(required = false) Long bookId) {
        List<Contact> contacts;
        if (bookId == null)
            contacts = contactService.getAll();
        else
            contacts = contactService.getByBookId(bookId);
        System.out.println("Total books found: " + contacts.size());
        return contacts.stream().map(contact -> convertToDTO(contact)).collect(Collectors.toList());
    }

    @GetMapping(value = "/contact/unique")
    public List<ContactDTO> getUniqueContact() {
        List<ContactDTO> ContactDTOS = contactService.getAll().stream().map(contact -> convertToDTO(contact)).collect(Collectors.toList());
        return ContactDTOS.stream().filter(contactService.distinctByKeys(ContactDTO::getFirstName, ContactDTO::getLastName, ContactDTO::getPhoneNumber)).collect(Collectors.toList());
    }

    @GetMapping(value = "/contact/{id}")
    public ContactDTO getById(@PathVariable long id) {
        return convertToDTO(contactService.getById(id));
    }

    @PostMapping(value = "/contact")
    public ContactDTO create(@RequestBody ContactDTO contact) {
        return convertToDTO(contactService.create(convertToVo(contact)));
    }

    @DeleteMapping(value = "/contact/{id}")
    public void deleteById(@PathVariable long id) {
        contactService.deleteById(id);
    }


    private ContactDTO convertToDTO(Contact contact) {
        return modelMapper.map(contact, ContactDTO.class);
    }

    private Contact convertToVo(ContactDTO contact) {
        return modelMapper.map(contact, Contact.class);
    }

}
