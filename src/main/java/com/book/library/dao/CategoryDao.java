package com.book.library.dao;

import com.book.library.entity.Category;

public interface CategoryDao {
    /**
     * author: longnd
     * @param id
     * @return category
     */
    public Category getCategoryById(int id);


}
