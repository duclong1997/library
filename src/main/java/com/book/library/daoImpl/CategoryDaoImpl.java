package com.book.library.daoImpl;

import com.book.library.dao.CategoryDao;
import com.book.library.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional
@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    EntityManager entityManager;
    @Override
    public Category getCategoryById(int id) {
        String sql = "select * from category where idcategory =:idCategory and enable <> 0";
        List<Category> categories = entityManager.createNativeQuery(sql, Category.class)
                .setParameter("idCategory", id).getResultList();
        if(categories.isEmpty())
        {
            return null;
        }
        else{
            return categories.get(0);
        }
    }
}
