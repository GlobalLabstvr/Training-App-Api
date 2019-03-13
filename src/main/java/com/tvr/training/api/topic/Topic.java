package com.tvr.training.api.topic;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tvr.training.api.subject.Subject;
import com.tvr.training.api.subject.SubjectId;

/**
 * Created by rajeevkumarsingh on 21/11/17.
 */
@Entity
@Table(name = "topics")
public class Topic  {
	 @EmbeddedId
	 private TopicId id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @NotNull
    @Size(max = 250)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "subjectId",
            referencedColumnName = "subject_id")
    private Subject subject;
   

    public TopicId getId() {
		return id;
	}

	public void setId(TopicId id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}