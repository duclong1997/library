package com.book.library.dao;

import com.book.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface BookRespository extends JpaRepository<Book, Long> {

    @Query(value = "select * from book where enable <> 0", nativeQuery = true)
    List<Book> getBooks();

    @Query(value = "select * from book where idbook =:idBook and enable =2 limit 1", nativeQuery = true)
    Book getBookBorrowedById(@Param("idBook") Integer idBook);

}
