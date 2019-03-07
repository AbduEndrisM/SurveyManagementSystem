package com.mum.groupproject.survey.dao;

import org.springframework.stereotype.Repository;

import com.mum.groupproject.survey.domain.Admin;

import java.util.*;

@Repository
public class AdminDao extends GenericDao<Admin> {

	public List<Admin> all() {
		List<Admin> list = new ArrayList<>();
		for (Admin admin : allObejcts()) {
			if (!admin.isDeleted()) {
				list.add(admin);
			}
		}
		return list;
	}

	public Admin findByUsername(final String username) {
		for (Admin admin : allObejcts()) {
			if (admin.getUsername().equals(username)) {
				return admin;
			}
		}
		return null;
	}

}
