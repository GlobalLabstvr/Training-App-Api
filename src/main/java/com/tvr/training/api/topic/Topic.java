package com.tvr.training.api.topic;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tvr.training.api.subject.Subject;


/**
 * Created by rajeevkumarsingh on 21/11/17.
 */
@Entity
@Table(name = "topics")
public class Topic  {
	
    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @NotNull
    @Size(max = 250)
    private String description;

  
    @EmbeddedId
    private TopicId id; 

    @MapsId("subjectId")
    @JoinColumns({
        @JoinColumn(name="SubjectID", referencedColumnName="SubjectID"),
        @JoinColumn(name="CourseID", referencedColumnName="CourseID")
    })
    @ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.ALL)
    private Subject subject;



	public String getName() {
        return name;
    }

    public TopicId getId() {
		return id;
	}

	public void setId(TopicId id) {
		this.id = id;
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