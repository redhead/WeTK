/*
 */
package org.wetk.back;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.wetk.business.local.ILesson;
import org.wetk.dto.LessonDTO;
import org.wetk.entity.Lesson;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "lessons")
@RequestScoped
public class LessonsBean {

	@EJB
	private ILesson lessonModel;

	private LessonDTO lesson = new LessonDTO();

	private Long classId;

	private Long assignmentId;


	public LessonsBean() {
		lesson.setHour(1);
	}


	public LessonDTO getLesson() {
		return lesson;
	}


	public String edit(Long id) throws Exception {
		Lesson l = lessonModel.find(id);
		if(l == null) {
			throw new Exception("Lesson not found");
		}
		lesson = new LessonDTO(l);
		classId = lesson.getClassId();
		assignmentId = lesson.getAssignmentId();
		return null;
	}


	public String delete(Long id) throws Exception {
		lessonModel.delete(id);
		return null;
	}


	public String saveLesson() {
		lessonModel.save(lesson, classId, assignmentId);
		return "success";
	}


	public List<LessonDTO> getLessons() {
		List<Lesson> lessons = lessonModel.getAll();
		List<LessonDTO> dtos = new ArrayList<LessonDTO>();
		for(Lesson l : lessons) {
			dtos.add(new LessonDTO(l));
		}
		return dtos;
	}


	public Long getAssignmentId() {
		return assignmentId;
	}


	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}


	public Long getClassId() {
		return classId;
	}


	public void setClassId(Long classId) {
		this.classId = classId;
	}

}
