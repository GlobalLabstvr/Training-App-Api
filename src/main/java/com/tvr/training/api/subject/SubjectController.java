package com.tvr.training.api.subject;

import com.tvr.training.api.course.CourseRepository;
import com.tvr.training.api.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CourseRepository courseRepository;
    
    @GetMapping("/subjects")
    public List<Subject> getAllsubjects( ) {
        return subjectRepository.findAll( );
    }

    
    @PostMapping("/courses/{courseId}/subjects")
    public Subject createSubject(@PathVariable (value = "courseId") Long courseId,
                                 @Valid @RequestBody Subject subject) {
        return courseRepository.findById(courseId).map(course -> {
            subject.setCourse(course);
            return subjectRepository.save(subject);
        }).orElseThrow(() -> new ResourceNotFoundException("courseId " + courseId + " not found"));
    }

    @PutMapping("/courses/{courseId}/subjects/{subjectId}")
    public Subject updateSubject(@PathVariable (value = "courseId") Long courseId,
                                 @PathVariable (value = "subjectId") Long subjectId,
                                 @Valid @RequestBody Subject subjectRequest) {
        if(!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("courseId " + courseId + " not found");
        }

        return subjectRepository.findById(subjectId).map(subject -> {
            subject.setName(subjectRequest.getName());
            subject.setDescription(subjectRequest.getDescription());
            return subjectRepository.save(subject);
        }).orElseThrow(() -> new ResourceNotFoundException("SubjectId " + subjectId + "not found"));
    }

    @DeleteMapping("/courses/{courseId}/subjects/{subjectId}")
    public ResponseEntity<?> deleteSubject(@PathVariable (value = "courseId") Long courseId,
                              @PathVariable (value = "subjectId") Long subjectId) {
        return subjectRepository.findByIdAndCourseId(subjectId, courseId).map(subject -> {
            subjectRepository.delete(subject);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Subject not found with id " + subjectId + " and courseId " + courseId));
    }
}
