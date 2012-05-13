/*
 */
package org.wetk.business.notificator;

import java.util.Calendar;
import java.util.Date;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.*;
import org.wetk.business.local.ILesson;
import org.wetk.business.local.ILessonEntry;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@MessageDriven(mappedName = "jmsEmailQueue", activationConfig = {
	@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class EmailMessageBean implements MessageListener {

	@EJB
	private ILesson lessonModel;

	@EJB
	private ILessonEntry entryModel;


	@Override
	public void onMessage(Message message) {
		try {
			if(!(message instanceof ObjectMessage)) return;

			ObjectMessage objMsg = (ObjectMessage) message;
			EmailMessageObject obj = (EmailMessageObject) objMsg.getObject();

			Long classId = obj.getClassId();
			String to = obj.getEmail();
			Date date = obj.getDateOfWeek();

			long total = getTotalCount(classId);
			long enteredThisWeek = getThisWeekCount(classId, date);
			
			message.acknowledge();

			/** @TODO send mail */
			// --------------------------
			// obj.setFrom("etk@etk.cz");
			// obj.setRecipient(to);
			// obj.setBody("Tento týden bylo zapsáno " + enteredThisWeek + " z " + total);
			// Transport.send(obj);
			
			System.out.println("-------- NOTIFICATION SEND --------");
			System.out.println("TO: " + to);
			System.out.println("BODY: Tento týden bylo zapsáno " + enteredThisWeek + " z " + total);
			System.out.println("classId: " + classId);
			System.out.println("-----------------------------------");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}


	private long getTotalCount(Long classId) {
		return lessonModel.getLessonCountFor(classId);
	}


	private long getThisWeekCount(Long classId, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date from = cal.getTime();

		cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		Date to = cal.getTime();

		return entryModel.getEntryCount(classId, from, to);
	}

}
