package com.tvr.training.api.subject;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rajeevkumarsingh on 21/11/17.
 */

@Repository

public interface SubjectRepository extends JpaRepository<Subject, SubjectId> {

	List<Subject> findByCourseId(Long courseId);

	Subject findByIdCourseIdAndIdSubjectId(Long courseId, Long subjectId);
	
}
