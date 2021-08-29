package org.techtask.addressbook.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = Book.TABLE_NAME)
public class Book {
    public static final String TABLE_NAME = "tblt_address_book";
    private static final String ID = "id";
    private static final String NAME = "name";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, nullable = false)
    private Long id;

    @Column(name = NAME, nullable = false)
    private String name;


}
