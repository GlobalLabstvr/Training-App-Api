package com.example.jpa.controller;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Playlist;
import com.example.jpa.model.Subject;
import com.example.jpa.repository.PlaylistRepository;
import com.example.jpa.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
public class PlaylistController {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private TopicRepository topicRepository;
    
    @GetMapping("/playlists")
    public List<Playlist> getAllplaylists( ) {
        return playlistRepository.findAll( );
    }

    
    @PostMapping("/topics/{topicId}/playlists")
    public Playlist createPlaylist(@PathVariable (value = "topicId") Long topicId,
                                 @Valid @RequestBody Playlist playlist) {
        return topicRepository.findById(topicId).map(topic -> {
            playlist.setTopic(topic);
            return playlistRepository.save(playlist);
        }).orElseThrow(() -> new ResourceNotFoundException("topicId " + topicId + " not found"));
    }

    
    
    @PutMapping("/topics/{topicId}/playlists/{playlistId}")
    public Playlist updateplaylist(@PathVariable (value = "topicId") Long topicId,
                                 @PathVariable (value = "playlistId") Long playlistId,
                                 @Valid @RequestBody Playlist playlistRequest) {
        if(!topicRepository.existsById(topicId)) {
            throw new ResourceNotFoundException("topicId " + topicId + " not found");
        }

        return playlistRepository.findById(playlistId).map(playlist -> {
            playlist.setName(playlistRequest.getName());
            playlist.setDescription(playlistRequest.getDescription());
            playlist.setUrl(playlistRequest.getUrl());
            return playlistRepository.save(playlist);
        }).orElseThrow(() -> new ResourceNotFoundException("playlistId " + playlistId + "not found"));
    }

    @DeleteMapping("/topics/{topicId}/playlists/{playlistId}")
    public ResponseEntity<?> deleteplaylist(@PathVariable (value = "topicId") Long topicId,
                              @PathVariable (value = "playlistId") Long playlistId) {
        return playlistRepository.findByIdAndTopicId(playlistId, topicId).map(playlist -> {
            playlistRepository.delete(playlist);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("playlist not found with id " + playlistId + " and topicId " + topicId));
    }
}
