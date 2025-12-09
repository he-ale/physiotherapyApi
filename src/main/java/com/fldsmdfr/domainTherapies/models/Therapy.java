package com.fldsmdfr.domainTherapies.models;

import java.time.LocalDate;

import com.fldsmdfr.domainTherapies.models.utilities.TherapyStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Therapy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    private Integer durationMin;

    private Double priceBase;

    private String category;

    @Enumerated(EnumType.STRING)
    private TherapyStatus status;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
        this.status = TherapyStatus.AVAILABLE;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }

    public String toString() {
        return "Therapy{id=" + id + ", name='" + name + "', description='" + description + "', durationMin=" + durationMin +
               ", priceBase=" + priceBase + ", category='" + category + "', status=" + status +
               ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "}";
    }
}
