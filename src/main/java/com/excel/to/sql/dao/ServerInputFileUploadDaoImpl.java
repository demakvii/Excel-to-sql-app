package com.excel.to.sql.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excel.to.sql.entity.ServerInputFileUpload;

@Transactional
@Repository
public class ServerInputFileUploadDaoImpl implements IServerInputFileUploadDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ServerInputFileUpload uploadToServer(ServerInputFileUpload fileUpload) {
		sessionFactory.getCurrentSession().save(fileUpload);
		return getFileFromServer(fileUpload.getFileName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServerInputFileUpload getFileFromServer(String fileName) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery(
				"findServerFileByName");
		query.setString("fileName", fileName);
		List<ServerInputFileUpload> resultList = query.list();

		if (null != resultList && resultList.size() > 0)
			return resultList.get(0);

		return null;
	}

	@Override
	public void flushFile(String fileName) {
		ServerInputFileUpload existFile = getFileFromServer(fileName);
		if (null != existFile) {
			sessionFactory.getCurrentSession().delete(existFile);
		}
	}

	@Override
	public void flushAll() {
		Query query = sessionFactory.getCurrentSession().createSQLQuery(
				"delete from mak_excel_to_sql_input");
		Query query1 = sessionFactory.getCurrentSession().createSQLQuery(
				"delete from mak_excel_to_sql_output");

		query.executeUpdate();
		query1.executeUpdate();
	}

	@Override
	public ServerInputFileUpload getFileFromServerById(Long id) {
		System.out.println(sessionFactory.getCurrentSession());
		return (ServerInputFileUpload) sessionFactory.getCurrentSession().get(
				ServerInputFileUpload.class, id);
	}

}
