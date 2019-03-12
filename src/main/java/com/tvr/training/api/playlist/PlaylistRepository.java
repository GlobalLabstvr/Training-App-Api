package com.tvr.training.api.playlist;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by rajeevkumarsingh on 21/11/17.
 */
@Repository


public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

	Optional<Playlist> findByIdAndTopicId(Long id, Long topicId);
   
}
