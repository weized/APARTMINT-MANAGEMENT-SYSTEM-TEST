package com.oopfinals.OOP.model.landlordmodel;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;           // Optional title for the announcement
    private String content;         // Main message/content
    private String target;          // 'all', 'notice', or specific room number

    private LocalDateTime postedAt; // Timestamp when posted

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = true)
    private Room room;              // Linked room (nullable)

    // Default constructor
    public Announcement() {
        this.postedAt = LocalDateTime.now();
    }

    // Simplified constructor
    public Announcement(String content, String target, Room room) {
        this.content = content;
        this.target = target;
        this.room = room;
        this.postedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getTarget() { return target; }
    public void setTarget(String target) { this.target = target; }

    public LocalDateTime getPostedAt() { return postedAt; }
    public void setPostedAt(LocalDateTime postedAt) { this.postedAt = postedAt; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    // Alias for compatibility with controller using 'description'
    public void setDescription(String description) {
        this.content = description;
    }

    public String getDescription() {
        return this.content;
    }
}
