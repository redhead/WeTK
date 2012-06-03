/*
 */
package org.wetk.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.wetk.entity.Lesson;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class TimeTable {

	private List<String> hours = new ArrayList<String>();

	private List<LessonDTO> mon = new ArrayList<LessonDTO>();

	private List<LessonDTO> tue = new ArrayList<LessonDTO>();

	private List<LessonDTO> wed = new ArrayList<LessonDTO>();

	private List<LessonDTO> thu = new ArrayList<LessonDTO>();

	private List<LessonDTO> fri = new ArrayList<LessonDTO>();


	public TimeTable(List<Lesson> lessons) {
		int count = 0;
		for(Lesson lesson : lessons) {
			List<LessonDTO> list = getList(lesson);
			
			if(list == null) continue;

			int hour = lesson.getHour();
			sizeTo(list, hour);

			list.set(hour - 1, new LessonDTO(lesson));

			if(hour > count) {
				count = hour;
			}
		}
		sizeTo(mon, count);
		sizeTo(tue, count);
		sizeTo(wed, count);
		sizeTo(thu, count);
		sizeTo(fri, count);

		for(int i = 0; i < count; i++) {
			hours.add((i + 1) + ".");
		}
	}


	private List<LessonDTO> getList(Lesson l) {
		switch(l.getDay()) {
			case Calendar.MONDAY:
				return mon;
			case Calendar.TUESDAY:
				return tue;
			case Calendar.WEDNESDAY:
				return wed;
			case Calendar.THURSDAY:
				return thu;
			case Calendar.FRIDAY:
				return fri;
		}
		return null;
	}


	private void sizeTo(List<LessonDTO> list, int len) {
		for(int i = list.size(); i < len; i++) {
			list.add(null);
		}
	}


	public List<LessonDTO> getMonTable() {
		return mon;
	}


	public List<LessonDTO> getTueTable() {
		return tue;
	}


	public List<LessonDTO> getWedTable() {
		return wed;
	}


	public List<LessonDTO> getThuTable() {
		return thu;
	}


	public List<LessonDTO> getFriTable() {
		return fri;
	}


	public List<String> getHourList() {
		return hours;
	}

}
