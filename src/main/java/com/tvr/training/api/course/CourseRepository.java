package com.tvr.training.api.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rajeevkumarsingh on 21/11/17.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
