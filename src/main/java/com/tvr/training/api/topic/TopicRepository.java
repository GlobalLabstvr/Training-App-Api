package com.tvr.training.api.topic;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface TopicRepository extends JpaRepository<Topic, Long> {

	List<Topic> findBySubjectId(Long subjectId);

	Optional<Topic> findByIdAndSubjectId(Long id, Long subjectId);
	
}
