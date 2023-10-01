package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJBDC implements DepartmentDao {
	Connection conn;
	
	public DepartmentDaoJBDC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Department department) {
	}

	@Override
	public void update(Department department) {
	}

	@Override
	public void deleteById(Integer id) {		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT department.* "
					+ "FROM department "
					+ "WHERE Id = ? ");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Department department = instantiateDepartment(rs);
				return department;
			} 
			return null;
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT department.* "
					+ "FROM department ");
			
			rs = st.executeQuery();
			List<Department> list = new ArrayList<>();
			
			while (rs.next()) {
				Department department = instantiateDepartment(rs);
				list.add(department);
			} 
			
			return list;
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		
		return dep;
	}

}
