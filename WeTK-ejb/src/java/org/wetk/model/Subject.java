/*
 */
package org.wetk.model;

import javax.persistence.*;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Subject.GET_ALL_SUBJECTS, query = "SELECT s FROM Subject s ORDER BY s.code")
})
public class Subject extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public static final String GET_ALL_SUBJECTS = "Subject.getAllSubjects";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;

	private String code;


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


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

}
