package com.tvr.training.api.subject;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tvr.training.api.course.Course;

@Embeddable
public class SubjectId implements Serializable {
 
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id")
    private Course course;
 
    @Column(name = "subject_id")
    private Long subjectId;
 
    public SubjectId() {
    }
 
    public SubjectId(Course course, Long subjectId) {
        this.course = course;
        this.subjectId = subjectId;
    }
 
    public Course getCourse() {
        return course;
    }
 
    public Long getSubjectId() {
        return subjectId;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubjectId)) return false;
        SubjectId that = (SubjectId) o;
        return Objects.equals(getCourse(), that.getCourse()) &&
                Objects.equals(getSubjectId(), that.getSubjectId());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getCourse(), getSubjectId());
    }
}
