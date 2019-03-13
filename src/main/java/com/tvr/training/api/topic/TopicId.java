package com.tvr.training.api.topic;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.tvr.training.api.subject.SubjectId;

@Embeddable
public class TopicId implements Serializable {
 
	 private SubjectId subjectId;
	 private Long topicId;
 
	
    
    public TopicId() {
    }
 
    public TopicId(SubjectId subjectId, Long topicId) {
        this.topicId = topicId;
        this.subjectId = subjectId;
    }

	public SubjectId getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(SubjectId subjectId) {
		this.subjectId = subjectId;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
		result = prime * result + ((topicId == null) ? 0 : topicId.hashCode());
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
		TopicId other = (TopicId) obj;
		if (subjectId == null) {
			if (other.subjectId != null)
				return false;
		} else if (!subjectId.equals(other.subjectId))
			return false;
		if (topicId == null) {
			if (other.topicId != null)
				return false;
		} else if (!topicId.equals(other.topicId))
			return false;
		return true;
	}
 
}
