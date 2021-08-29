package org.techtask.addressbook.controller.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class BookDTO {
    private Long id;
    private String name;
}
