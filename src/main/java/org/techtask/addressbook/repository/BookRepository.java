package org.techtask.addressbook.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.techtask.addressbook.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
