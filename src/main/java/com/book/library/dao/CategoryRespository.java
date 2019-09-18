package com.book.library.dao;

import com.book.library.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CategoryRespository extends JpaRepository<Category, Long> {

    @Query(value = "select * from category where idcategory =:idCategory and enable <> 0 limit 1", nativeQuery = true)
    Category getCategoryById(@Param("idCategory") Integer idCategory);
}
