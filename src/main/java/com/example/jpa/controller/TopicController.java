package com.example.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jpa.exception.ResourceNotFoundException;

import com.example.jpa.model.Topic;
import com.example.jpa.repository.TopicRepository;

import java.util.List;

import javax.validation.Valid;

@RestController
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/topics")
    public List<Topic> getAlltopics( ) {
        return topicRepository.findAll( );
    }

    @PostMapping("/topics/{topicId}")
    public Topic createtopic(@Valid @RequestBody Topic topic) {
        return topicRepository.save(topic);
    }

    @PutMapping("/topics/{topicId}")
    public Topic updatetopic(@PathVariable Long topicId, @Valid @RequestBody Topic topicRequest) {
        return topicRepository.findById(topicId).map(topic -> {
            topic.setName(topicRequest.getName());
            topic.setDescription(topicRequest.getDescription());
            
            return topicRepository.save(topic);
        }).orElseThrow(() -> new ResourceNotFoundException("topicId " + topicId + " not found"));
    }


    @DeleteMapping("/topics/{topicId}")
    public ResponseEntity<?> deletetopic(@PathVariable Long topicId) {
        return topicRepository.findById(topicId).map(topic -> {
            topicRepository.delete(topic);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("topicId " + topicId + " not found"));
    }

}
