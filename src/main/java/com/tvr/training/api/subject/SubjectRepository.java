package com.tvr.training.api.subject;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Created by rajeevkumarsingh on 21/11/17.
 */

@Repository

public interface SubjectRepository extends JpaRepository<Subject, Long> 
{
    
Subject findByCourseId(Long courseId);
    
Optional<Subject> findByIdAndCourseId(Long id, Long courseId);

}
