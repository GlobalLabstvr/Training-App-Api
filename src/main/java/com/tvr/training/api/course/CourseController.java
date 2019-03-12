package com.tvr.training.api.course;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jpa.exception.ResourceNotFoundException;

import java.util.List;

import javax.validation.Valid;

@RestController
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses")
    public List<Course> getAllcourses( ) {
        return courseRepository.findAll( );
    }

    @PostMapping("/courses")
    public Course createcourse(@Valid @RequestBody Course course) {
        return courseRepository.save(course);
    }

    @PutMapping("/courses/{courseId}")
    public Course updatecourse(@PathVariable Long courseId, @Valid @RequestBody Course courseRequest) {
        return courseRepository.findById(courseId).map(course -> {
            course.setName(courseRequest.getName());
            course.setDescription(courseRequest.getDescription());
            
            return courseRepository.save(course);
        }).orElseThrow(() -> new ResourceNotFoundException("courseId " + courseId + " not found"));
    }


    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<?> deletecourse(@PathVariable Long courseId) {
        return courseRepository.findById(courseId).map(course -> {
            courseRepository.delete(course);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("courseId " + courseId + " not found"));
    }

}
