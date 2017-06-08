package fr.humanbooster.liaison.dao.impl;

import java.sql.SQLException;

import fr.humanbooster.liaison.dao.PersonneDao;

public class TestDaoImpl {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		PersonneDao pDao = new PersonneDaoImpl();
		System.out.println(pDao.changePassword("jon", 20l));
	}

}
