package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDAO {
	public static Connection conn;

	private int pageNo;
	private int pageSize = 10;

	public BaseDAO(Connection conn) {
		this.conn = conn;
	}

	public void save(String query, Object[] vals) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(query);
		if (vals != null) {
			int count = 1;
			for (Object o : vals) {
				pstmt.setObject(count, o);
				count++;
			}
		}
		pstmt.executeUpdate();
	}

	public Integer saveWithID(String query, Object[] vals) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS);
		if (vals != null) {
			int count = 1;
			for (Object o : vals) {
				pstmt.setObject(count, o);
				count++;
			}
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		conn.commit();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			return -1;
		}
	}

	public <T> List<T> read(String query, Object[] vals) throws SQLException {
//		int pageNo = getPageNo();
//		int limit = 0;
//		if (pageNo == 0) {
//			limit = 0;
//			query = query + " LIMIT " + limit + " , " + getPageSize();
//		} else if (pageNo == -1) {
//			System.out.println("-1");
//			//query = query + " LIMIT " + limit + " , " + getPageSize();
//		} else {
//			limit = (pageNo - 1) * getPageSize();
//			query = query + " LIMIT " + limit + " , " + getPageSize();
//		}
		PreparedStatement pstmt = conn.prepareStatement(query);
		if (vals != null) {
			int count = 1;
			for (Object o : vals) {
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return extractData(rs);
	}

	public abstract <T> List<T> extractData(ResultSet rs);

	public <T> List<T> readFirstLevel(String query, Object[] vals)
			throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(query);
		if (vals != null) {
			int count = 1;
			for (Object o : vals) {
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return extractDataFirstLevel(rs);
	}

	public Integer getCount(String query, Object[] vals) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(query);
		if (vals != null) {
			int count = 1;
			for (Object o : vals) {
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			return -1;
		}

	}

	public abstract <T> List<T> extractDataFirstLevel(ResultSet rs);

	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
