package com.tvr.training.api.topic;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tvr.training.api.exception.ResourceNotFoundException;
import com.tvr.training.api.subject.Subject;
import com.tvr.training.api.subject.SubjectId;
import com.tvr.training.api.subject.SubjectRepository;

@RestController
public class TopicController {

	@Autowired
	private TopicRepository TopicRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@GetMapping("/topics")
	public List<Topic> getAllTopics() {
		return TopicRepository.findAll();
	}
	
	@GetMapping("/courses/{courseId}/subjects/{subjectId}/topics")
	public List<Topic> getTopicByCourseIdAndSubjectId(
			@PathVariable(value = "courseId") Long courseId,
			@PathVariable(value = "subjectId") Long subjectId) {
		return TopicRepository.findByIdSubjectId(new SubjectId(courseId,subjectId));
	}

	@GetMapping("/courses/{courseId}/subjects/{subjectId}/topics/{topicId}")
	public Optional<Topic> getTopics(
			@PathVariable(value = "courseId") Long courseId,
			@PathVariable(value = "subjectId") Long subjectId,
			@PathVariable(value = "topicId") Long topicId) {
		return TopicRepository.findByIdTopicIdAndIdSubjectId(topicId, new SubjectId(courseId,subjectId));
	}

	@PostMapping("/courses/{courseId}/subjects/{subjectId}/topics/{topicId}")
	public Topic createTopic(
			@PathVariable(value = "courseId") Long courseId, 
			@PathVariable(value = "subjectId") Long subjectId, 
			@PathVariable(value = "topicId") Long topicId, 
			@Valid @RequestBody Topic Topic) {
		Subject subject = subjectRepository.findByIdCourseIdAndIdSubjectId(courseId,subjectId);
		if(subject!=null) {
			TopicId id = new TopicId(courseId,subjectId,topicId);
			Topic.setId(id);
			Topic.setSubject(subject);
			return TopicRepository.save(Topic);
		}
		else {
			throw new ResourceNotFoundException("subjectId " + subjectId + " not found");
		}
	}

	/*@PutMapping("/subjects/{subjectId}/topics/{topicId}")
	public Topic updateTopic(@PathVariable(value = "subjectId") Long subjectId,
			@PathVariable(value = "TopicId") Long TopicId, @Valid @RequestBody Topic TopicRequest) {
		if (!subjectRepository.existsById(subjectId)) {
			throw new ResourceNotFoundException("subjectId " + subjectId + " not found");
		}

		return TopicRepository.findById(TopicId).map(Topic -> {
			Topic.setName(TopicRequest.getName());
			Topic.setDescription(TopicRequest.getDescription());
			return TopicRepository.save(Topic);
		}).orElseThrow(() -> new ResourceNotFoundException("TopicId " + TopicId + "not found"));
	}*/

	@DeleteMapping("courses/{courseId}/subjects/{subjectId}/topics/{topicId}")
	public ResponseEntity<?> deleteTopic(
			@PathVariable(value = "courseId") Long courseId,
			@PathVariable(value = "subjectId") Long subjectId,
			@PathVariable(value = "TopicId") Long TopicId) {
		return TopicRepository.findByIdTopicIdAndIdSubjectId(TopicId, new SubjectId(courseId,subjectId)).map(Topic -> {
			TopicRepository.delete(Topic);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(
				"Topic not found with id " + TopicId + " and subjectId " + subjectId));
	}
}
