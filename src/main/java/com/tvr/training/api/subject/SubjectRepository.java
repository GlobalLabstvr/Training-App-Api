package com.tvr.training.api.subject;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rajeevkumarsingh on 21/11/17.
 */

@Repository

public interface SubjectRepository extends JpaRepository<Subject, Long> {

	List<Subject> findByCourseId(Long courseId);

	Optional<Subject> findByIdAndCourseId(Long id, Long courseId);
	
}
