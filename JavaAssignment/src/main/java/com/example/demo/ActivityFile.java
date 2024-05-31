package com.example.demo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ActivityFile {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	  @JsonProperty("uniqueId")
	  private Long uniqueId;

	    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Activity> activities;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		

		public List<Activity> getActivities() {
			return activities;
		}

		public void setActivities(List<Activity> activities) {
			this.activities = activities;
		}
	    
}
