package com.example.demo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Activity {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	    private String name;

	    private LocalDateTime time;

	    private int duration;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public LocalDateTime getTime() {
			return time;
		}

		public void setTime(LocalDateTime time) {
			this.time = time;
		}

		public int getDuration() {
			return duration;
		}

		public void setDuration(int duration) {
			this.duration = duration;
		}
	    
	    
}
