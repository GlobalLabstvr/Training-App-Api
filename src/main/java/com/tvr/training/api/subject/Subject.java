package com.tvr.training.api.subject;

import com.fasterxml.jackson.annotation.*;
import com.tvr.training.api.course.Course;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


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

    @MapsId("courseId")
    @JoinColumn(name = "course_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("course_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Course course;

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
