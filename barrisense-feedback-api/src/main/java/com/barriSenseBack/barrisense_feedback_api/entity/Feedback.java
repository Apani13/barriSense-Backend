package com.barriSenseBack.barrisense_feedback_api.entity;


import jakarta.persistence.*;
import lombok.Getter;



@Entity
@Table(name = "feedbacks")

public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "hood_id", nullable = false)
    private Long hoodId;

    @Column(name = "hood_name", nullable = false)
    private String hoodName;

    @Column(nullable = false, length = 1000)
    private String content;


    public Feedback() {
    }


    public Feedback(Long userId, Long hoodId, String hoodName, String content) {
        this.userId = userId;
        this.hoodId = hoodId;
        this.hoodName = hoodName;
        this.content = content;
    }

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getHoodId() {
        return hoodId;
    }

    public void setHoodId(Long hoodId) {
        this.hoodId = hoodId;
    }

    public String getHoodName() {
        return hoodName;
    }

    public void setHoodName(String hoodName) {
        this.hoodName = hoodName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
