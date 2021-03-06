package com.tvr.training.api.playlist;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.tvr.training.api.subject.Subject;
import com.tvr.training.api.topic.Topic;
import com.tvr.training.api.topic.TopicId;

/**
 * Created by rajeevkumarsingh on 21/11/17.
 */
@Entity
@Table(name = "playlists")
public class Playlist  {
    @EmbeddedId
    private PlaylistId id;
    
    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @NotNull
    @Size(max = 250)
    private String description;
    
    @NotNull
    @Size(max = 250)
    private String url;
    
    
    
     
     
    @MapsId("topicId")
     @JoinColumns({
    	 @JoinColumn(name="CourseID", referencedColumnName="CourseID"),
          @JoinColumn(name="SubjectID", referencedColumnName="SubjectID"),
         @JoinColumn(name="TopicID", referencedColumnName="TopicID")
     })
     @ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.ALL)
     private Topic topic; 

    public PlaylistId getId() {
        return id;
    }

    public void setId(PlaylistId id) {
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
    
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}