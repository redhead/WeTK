/*
 */
package org.wetk.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@StaticMetamodel(Lesson.class)
public class Lesson_ {

	public static volatile SingularAttribute<Lesson, Long> id;

	public static volatile SingularAttribute<Lesson, Integer> day;

	public static volatile SingularAttribute<Lesson, Integer> hour;

	public static volatile SingularAttribute<Lesson, SubjectAssignment> assignment;

	public static volatile SingularAttribute<Lesson, ClassEntity> clazz;

}
