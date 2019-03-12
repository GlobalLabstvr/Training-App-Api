package com.tvr.training.api.topic;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tvr.training.api.exception.ResourceNotFoundException;

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
