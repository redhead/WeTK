/*
 */
package org.wetk.back;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import org.wetk.business.local.ILesson;
import org.wetk.dto.LessonDTO;
import org.wetk.dto.SubjectDTO;
import org.wetk.model.Lesson;
import org.wetk.model.Subject;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "lessons")
@RequestScoped
public class LessonsBean {

	@EJB
	private ILesson model;

	private LessonDTO lesson = new LessonDTO();

	private Long classId;

	private Long assignmentId;


	public LessonDTO getLesson() {
		return lesson;
	}


	public String edit(Long id) throws Exception {
		Lesson l = model.find(id);
		if(l == null) {
			throw new Exception("Lesson not found");
		}
		lesson = new LessonDTO(l);
		classId = lesson.getClassId();
		assignmentId = lesson.getAssignmentId();
		return null;
	}


	public String delete(Long id) throws Exception {
		model.delete(id);
		return null;
	}


	public String saveSubject() {
		model.save(lesson, classId, assignmentId);
		return "success";
	}


	public List<LessonDTO> getSubjects() {
		List<Lesson> lessons = model.getAllLessons();
		List<LessonDTO> dtos = new ArrayList<LessonDTO>();
		for(Lesson l : lessons) {
			dtos.add(new LessonDTO(l));
		}
		return dtos;
	}

}