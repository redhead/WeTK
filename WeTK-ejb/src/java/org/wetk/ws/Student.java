/*
 */
package org.wetk.ws;

import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@WebService(serviceName = "Student")
@Stateless()
public class Student {

	@PersistenceContext
	private EntityManager manager;


	@WebMethod
	public Long getStudentId(String firstName, String lastName) {
		Query query = manager.createQuery("SELECT s.id FROM Student s WHERE s.firstName = :fn AND s.lastName = :ln");
		query.setParameter("fn", firstName);
		query.setParameter("ln", lastName);
		List list = query.getResultList();
		return (Long) list.get(0);
	}


	@WebMethod
	public long getUnexcusedAbsenceCount(Long id) {
		Query query = manager.createQuery("SELECT COUNT(a) FROM Absence a WHERE a.exuse IS NULL AND a.student.id = :id");
		query.setParameter("id", id);
		return (Long) query.getSingleResult();
	}

}
