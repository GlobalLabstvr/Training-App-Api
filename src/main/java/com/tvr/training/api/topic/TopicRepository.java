package com.tvr.training.api.topic;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvr.training.api.subject.SubjectId;


@Repository

public interface TopicRepository extends JpaRepository<Topic, TopicId> {

	//List<Topic> findBySubjectId(Long subjectId);
	
	List<Topic> findByIdSubjectId(SubjectId subjectId);

	Optional<Topic> findByIdTopicIdAndIdSubjectId(Long topicId,SubjectId subjectId);
	
}
