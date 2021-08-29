package org.techtask.addressbook.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = Contact.TABLE_NAME)
public class Contact {
    public static final String TABLE_NAME = "tblt_book_contact_dtls";
    private static final String ID = "id";
    private static final String NAME = "firstname";
    private static final String SURNAME = "lastname";
    private static final String PHONENUMBER = "phonenumber";
    private static final String BOOKID = "bookid";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, nullable = false)
    private Long id;

    @Column(name = NAME, nullable = false)
    private String firstName;

    @Column(name = SURNAME, nullable = true)
    private String lastName;

    @Column(name = PHONENUMBER, nullable = false)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = BOOKID, nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Book book;
}
