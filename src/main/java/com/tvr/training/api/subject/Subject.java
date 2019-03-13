package com.tvr.training.api.subject;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tvr.training.api.course.Course;
import com.tvr.training.api.topic.Topic;


@Entity
@Table(name = "subjects")
public class Subject {
	
	@EmbeddedId
	private SubjectId id;
	
    @NotNull
    @Lob
    private String name;
    
    @NotNull
	private String description;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinColumn(name = "CourseID", insertable = false, updatable = false)
    private Course course;
    
    
    @OneToMany(fetch = FetchType.EAGER,mappedBy="subject",cascade = CascadeType.ALL)
    private Set<Topic> topics;

    public SubjectId getId() {
		return id;
	}

	public void setId(SubjectId id) {
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
