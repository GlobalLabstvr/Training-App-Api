package com.tvr.training.api.playlist;

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
import com.tvr.training.api.subject.SubjectId;
import com.tvr.training.api.topic.Topic;
import com.tvr.training.api.topic.TopicRepository;
import com.tvr.training.api.topic.TopicId;

@RestController
public class PlaylistController {

	
	@Autowired
	private PlaylistRepository PlaylistRepository;//dont use capital letter for variable name
	
	@Autowired
	private TopicRepository TopicRepository;


	@GetMapping("/playlists")
	public List<Playlist> getAllPlaylists() {
		return PlaylistRepository.findAll();
	}

	@GetMapping("/courses/{courseId}/subjects/{subjectId}/topics/{topicId}/playlists/{playlistId}/")
	public Optional<Playlist> getPlaylists(
			@PathVariable(value = "courseId") Long courseId,
			@PathVariable(value = "subjectId") Long subjectId,
			@PathVariable(value = "topicId") Long topicId,
			@PathVariable(value = "playlistId") Long playlistId)	{
		return PlaylistRepository.findByIdTopicIdAndIdPlaylistId(new TopicId(courseId,subjectId,topicId), playlistId);
	}

	@GetMapping("/courses/{courseId}/subjects/{subjectId}/topics/{topicId}/playlists")
	public List<Playlist> getPlaylistsByTopicId(
			@PathVariable(value = "courseId") Long courseId,
			@PathVariable(value = "subjectId") Long subjectId,
			@PathVariable(value = "topicId") Long topicId
			) {
		return PlaylistRepository.findByIdTopicId(new TopicId(courseId,subjectId,topicId));
	}

	@PostMapping("/courses/{courseId}/subjects/{subjectId}/topics/{topicId}/playlists/{playlistId}")
	public Playlist createPlaylist(
			@PathVariable(value = "courseId") Long courseId,
			@PathVariable(value = "subjectId") Long subjectId,
			@PathVariable(value = "topicId") Long topicId, 
			@PathVariable(value = "playlistId") Long playlistId, 
			
			@Valid @RequestBody Playlist playlist) {
			Optional<Topic> topic = TopicRepository.findByIdTopicIdAndIdSubjectId(topicId,
					new SubjectId(courseId,subjectId));
		if(topic!=null) {
			PlaylistId id = new PlaylistId(courseId,subjectId,topicId,playlistId);
			playlist.setId(id);
			playlist.setTopic(topic.get());
			return playlist;
		}
		else {
			throw new ResourceNotFoundException("topicId " + topicId + " not found");
		}
	}

	/*@PutMapping("/playlists/{playlistId}/topics/{topicId}")
	public Topic updateTopic(@PathVariable(value = "playlistId") Long playlistId,
			@PathVariable(value = "TopicId") Long TopicId, @Valid @RequestBody Topic TopicRequest) {
		if (!playlistRepository.existsById(playlistId)) {
			throw new ResourceNotFoundException("playlistId " + playlistId + " not found");
		}

		return TopicRepository.findById(TopicId).map(Topic -> {
			Topic.setName(TopicRequest.getName());
			Topic.setDescription(TopicRequest.getDescription());
			return TopicRepository.save(Topic);
		}).orElseThrow(() -> new ResourceNotFoundException("TopicId " + TopicId + "not found"));
	}*/
	//please test
	//dont use UpperCamelCase for varibles;

	@DeleteMapping("courses/{courseId}/topics/{topicId}/playlists/{playlistId}")
	public ResponseEntity<?> deletePlaylist(
			@PathVariable(value = "courseId") Long courseId,
			@PathVariable(value = "topicId") Long topicId,
			@PathVariable(value = "PlaylistId") Long PlaylistId) {
		return PlaylistRepository.findByIdTopicIdAndIdPlaylistId( 
				new TopicId(courseId,topicId, PlaylistId), PlaylistId).map(Playlist -> {
			PlaylistRepository.delete(Playlist);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(
				"Playlist not found with id " + PlaylistId + " and topicId " + topicId));
	}
}
