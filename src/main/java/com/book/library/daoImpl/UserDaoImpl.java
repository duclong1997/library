package com.book.library.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.book.library.dao.UserDao;
import com.book.library.entity.User;

@Transactional
@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public User getUserByUsername(String username) {
		String sql ="select * from user where enable =1 and username= :username";
		List<User> listUser = entityManager.createNativeQuery(sql,User.class)
				.setParameter("username", username).getResultList();
		if (listUser.isEmpty())
		{
			return null;
		}
		else{
			return listUser.get(0);
		}
	}

	@Override
	public void insertUser(User user) {
		entityManager.persist(user);
	}

	@Override
	public void updateUser(User user) {
		entityManager.merge(user);
	}

	@Override
	public User getUserById(int idUser) {
		return entityManager.find(User.class, idUser);
	}

}
