package com.tvr.training.api.playlist;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.tvr.training.api.subject.SubjectId;
import com.tvr.training.api.topic.TopicId;

@Embeddable
public class PlaylistId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "PlaylistID")
    private Long playlistId;

	@Column(name = "TopicID")
    private TopicId topicId;

    

    public PlaylistId() {
    	
    }
    
    public PlaylistId(Long courseId, Long subjectId, Long topicId, Long playlistId) {
    	this.topicId = new TopicId(courseId,subjectId,topicId);
       	this.playlistId = playlistId;
    }
    
	public TopicId getTopicId() {
		return topicId;
	}

	public void setTopicId(TopicId topicId) {
		this.topicId = topicId;
	}

	public Long getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(Long playlistId) {
		this.playlistId = playlistId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((topicId == null) ? 0 : topicId.hashCode());
		result = prime * result + ((playlistId == null) ? 0 : playlistId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlaylistId other = (PlaylistId) obj;
		if (topicId == null) {
			if (other.topicId != null)
				return false;
		} else if (!topicId.equals(other.topicId))
			return false;
		if (playlistId == null) {
			if (other.playlistId != null)
				return false;
		} else if (!playlistId.equals(other.playlistId))
			return false;
		return true;
	}

    

}