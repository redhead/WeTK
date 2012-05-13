/*
 */
package org.wetk.business.notificator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timer;
import javax.jms.*;
import org.wetk.business.local.ITeacher;
import org.wetk.entity.ClassEntity;
import org.wetk.entity.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Singleton
@Startup
public class ClassTeacherNotification implements ClassTeacherNotificationLocal {

	@Resource(mappedName = "jmsEmailQueueFactory")
	private ConnectionFactory queueFactory;

	@Resource(mappedName = "jmsEmailQueue")
	private Queue queue;

	@EJB
	private ITeacher teacherModel;


	@Override
	//@Schedule(dayOfWeek = "Fri", hour = "18")
	public void notifyEndOfWeek(Timer timer) {
		try {
			Connection connection = queueFactory.createConnection();

			List<Teacher> teachers = teacherModel.getWithClass();
			for(Teacher t : teachers) {
				notifyTeacher(t, connection);
			}
		} catch(JMSException ex) {
			ex.printStackTrace();
		}
	}


	private void notifyTeacher(Teacher teacher, Connection connection) {
		try {
			Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(queue);
			
			ClassEntity clazz = teacher.getClazz();

			EmailMessageObject obj = new EmailMessageObject();

			obj.setClassId(clazz.getId());
			obj.setEmail(teacher.getEmail());
			obj.setDateOfWeek(getDate());

			ObjectMessage msg = session.createObjectMessage(obj);
			producer.send(msg);
		} catch(JMSException ex) {
			ex.printStackTrace();
		}
	}


	private Date getDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

}
