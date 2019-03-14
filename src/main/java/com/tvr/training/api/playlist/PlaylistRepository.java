package com.tvr.training.api.playlist;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvr.training.api.topic.Topic;
import com.tvr.training.api.topic.TopicId;
/**
 * Created by rajeevkumarsingh on 21/11/17.
 */

@Repository

public interface PlaylistRepository extends JpaRepository<Playlist, PlaylistId> {

	List<Playlist> findByIdTopicId(TopicId topicId);

	Optional<Playlist> findByIdTopicIdAndIdPlaylistId(
			TopicId topicId, Long playlistId);
}

