package com.example.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
	public class Paste {

	   

	@Id
    private String id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private Instant createdAt;
    private Instant expiresAt;
    private Integer maxViews;
    private Integer viewCount = 0;


		public Paste(String id, String content, Instant createdAt, Instant expiresAt, Integer maxViews,
				Integer viewCount) {
			super();
			this.id = id;
			this.content = content;
			this.createdAt = createdAt;
			this.expiresAt = expiresAt;
			this.maxViews = maxViews;
			this.viewCount = viewCount;
		}


		

		public Paste() {
			// TODO Auto-generated constructor stub
		}




		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public String getContent() {
			return content;
		}


		public void setContent(String content) {
			this.content = content;
		}


		public Instant getCreatedAt() {
			return createdAt;
		}


		public void setCreatedAt(Instant createdAt) {
			this.createdAt = createdAt;
		}


		public Instant getExpiresAt() {
			return expiresAt;
		}


		public void setExpiresAt(Instant expiresAt) {
			this.expiresAt = expiresAt;
		}


		public Integer getMaxViews() {
			return maxViews;
		}


		public void setMaxViews(Integer maxViews) {
			this.maxViews = maxViews;
		}


		public Integer getViewCount() {
			return viewCount;
		}


		public void setViewCount(Integer viewCount) {
			this.viewCount = viewCount;
		}


		@Override
		public String toString() {
			return "Paste [id=" + id + ", content=" + content + ", createdAt=" + createdAt + ", expiresAt=" + expiresAt
					+ ", maxViews=" + maxViews + ", viewCount=" + viewCount + "]";
		}
		@PrePersist
	    public void prePersist() {
	        if (this.id == null) {
	            this.id = UUID.randomUUID().toString();
	        }
	        if (this.createdAt == null) {
	            this.createdAt = Instant.now();
	        }
	        if (this.viewCount == null) {
	            this.viewCount = 0;
	        }
	    }

	    
	    
	    

	    
	    
	}


