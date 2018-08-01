package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO<Publisher> implements ResultSetExtractor <List<Publisher>>{

	public void createPublisher(Publisher publisher)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		template.update("INSERT INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?,?,?)", new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone() });
	}

	public void updatePublisher(Publisher publisher)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		template.update("update tbl_publisher set publisherName =?, publisherAddress =?, publisherPhone = ? where publisherId = ?", new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone() , publisher.getPublisherId() });
	}

	public void deletePublisher(Publisher publisher)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		template.update("delete from tbl_publisher where publisherId = ?", new Object[] { publisher.getPublisherId() });
	}

	public List<Publisher> readAllPublishers() throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_publisher", this);
	}

	public List<Publisher> readAllPublishersByName(String searchString) throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_publisher where publisherName = ?", new Object[] { searchString }, this);
	}
	
	public List<Publisher> readAllPublishersByBook(Integer publisherId) throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_publisher where publisherId IN (select publisherId from tbl_book where publisherId = ?", new Object[] { publisherId }, this);
	}

	public Publisher readPublisherIdrByPK(Integer primaryKey) throws ClassNotFoundException, SQLException {
		List<Publisher> publishers = template.query("select * from tbl_publisher where publisherId = ?", new Object[] { primaryKey }, this);
		if (!publishers.isEmpty()) {
			return publishers.get(0);
		}
		return null;
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<>();
		while (rs.next()) {
			Publisher publisher = new Publisher();
			publisher.setPublisherId(rs.getInt("publisherId"));
			publisher.setPublisherName(rs.getString("publisherName"));
			publisher.setPublisherAddress(rs.getString("publisherAddress"));
			publisher.setPublisherPhone(rs.getString("publisherPhone"));
			publishers.add(publisher);
		}
		return publishers;
	}
}
