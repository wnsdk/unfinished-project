//package com.jungto.suhaeng.domain;
//
//import jakarta.persistence.*;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//
//import java.time.LocalDateTime;
//
//public abstract class BaseTimeEntity {
//    @CreatedDate
//    @Column(name = "created_date", updatable = false, columnDefinition = "TIMESTAMP")
//    private LocalDateTime createdDate;
//
//    @LastModifiedDate
//    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
//    private LocalDateTime modifiedDate;
//
//    @PrePersist
//    public void prePersist() {
//        LocalDateTime now = LocalDateTime.now();
//        createdDate = now;
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        modifiedDate = LocalDateTime.now();
//    }
//}
