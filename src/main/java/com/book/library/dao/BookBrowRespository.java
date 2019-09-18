package com.book.library.dao;

import com.book.library.entity.BookBrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface BookBrowRespository extends JpaRepository<BookBrow,Long> {

    @Query(value = "select * from bookbrow where iduser = :idUser and idBook = :idBook and enable <> 0", nativeQuery = true)
    BookBrow getBookBrowByIdUserAndIdBook(@Param("idUser") Integer idUser, @Param("idBook") Integer idBook);

    @Query(value = "SELECT * FROM bookbrow where idbook =:idBook and end_brow <= now() and enable =1", nativeQuery = true)
    BookBrow checkTimeBorrowedBook(@Param("idBook") Integer idBook);

}
