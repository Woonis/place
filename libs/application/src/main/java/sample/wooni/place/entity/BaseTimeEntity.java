package sample.wooni.place.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@MappedSuperclass
public class BaseTimeEntity {
    @Column(name = "created_at", columnDefinition = "TIMESTAMP with time zone", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @Column(name = "modified_at", columnDefinition = "TIMESTAMP with time zone", nullable = false)
    private ZonedDateTime modifiedAt;

    @PrePersist
    protected void onCreate() {
        var now = ZonedDateTime.now();
        this.createdAt = now;
        this.modifiedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedAt = ZonedDateTime.now();
    }
}
