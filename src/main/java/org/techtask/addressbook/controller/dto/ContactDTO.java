package org.techtask.addressbook.controller.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ContactDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private BookDTO book;
}
