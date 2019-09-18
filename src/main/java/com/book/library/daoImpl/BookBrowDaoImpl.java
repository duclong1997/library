package com.book.library.daoImpl;

import com.book.library.dao.BookBrowDao;
import com.book.library.dao.BookDao;
import com.book.library.entity.BookBrow;
import com.book.library.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional
@Repository
public class BookBrowDaoImpl  implements BookBrowDao {

    @Autowired
    EntityManager entityManager;

    @Override
    public void borrowBook(BookBrow bookBrow) {
        entityManager.persist(bookBrow);
    }

    @Override
    public void returnBook(BookBrow bookBrow) {
        bookBrow.setEnable(Common.BookBorrow.RETURN_BOOK);
        entityManager.merge(bookBrow);
    }

    @Override
    public BookBrow getBookBrowByIdUserAndIdBook(int idUser, int idBook) {
        String sql = "select * from bookbrow where iduser = :idUser and idBook = :idBook and enable <> 0";
        List<BookBrow> bookBrows = entityManager.createNativeQuery(sql,BookBrow.class)
                .setParameter("idUser",idUser)
                .setParameter("idBook",idBook)
                .getResultList();
        if(bookBrows.isEmpty())
        {
            return  null;
        }
        else{
            return  bookBrows.get(0);
        }
    }

    @Override
    public BookBrow checkTimeBorrowedBook(int idBook) {
        String sql ="SELECT * FROM bookbrow where idbook =:idBook and end_brow <= now() and enable =1";
        List<BookBrow> bookBrows = entityManager.createNativeQuery(sql,BookBrow.class)
                .setParameter("idBook",idBook).getResultList();
        if (bookBrows.isEmpty())
        {
            return  null;
        }
        else {
            return  bookBrows.get(0);
        }
    }

    @Override
    public void updateBookBrow(BookBrow bookBrow) {
        entityManager.merge(bookBrow);
    }
}
