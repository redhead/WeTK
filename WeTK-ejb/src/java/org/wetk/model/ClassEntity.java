/*
 */
package org.wetk.model;

import java.util.List;
import javax.persistence.*;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Entity
@NamedQueries({
	@NamedQuery(name = ClassEntity.GET_BY_TITLE, query = "SELECT c FROM ClassEntity c WHERE c.title = :title"),
	@NamedQuery(name = ClassEntity.GET_ALL_SUBJECTS, query = "SELECT c FROM ClassEntity c ORDER BY c.title")
})
public class ClassEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public static final String GET_BY_TITLE = "ClassEntity.getByTitle";

	public static final String GET_ALL_SUBJECTS = "ClassEntity.getAllSubjects";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String title;

	@OneToMany(mappedBy = "clazz")
	@OrderBy("ordinal")
	private List<Student> students;

	@OneToOne(optional = false)
	@JoinColumn(name = "teacher_id", unique = true)
	private Teacher teacher;


	@Override
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public List<Student> getStudents() {
		return students;
	}


	public void setStudents(List<Student> students) {
		this.students = students;
	}


	public Teacher getTeacher() {
		return teacher;
	}


	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}
