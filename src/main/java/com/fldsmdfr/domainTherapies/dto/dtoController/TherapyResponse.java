package com.fldsmdfr.domainTherapies.dto.dtoController;

import java.time.LocalDate;

import com.fldsmdfr.domainTherapies.models.utilities.TherapyStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TherapyResponse{
    private Long id;
    private String name;
    private String description;
    private Integer durationMin;
    private Double priceBase;
    private String category;
    private TherapyStatus status;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
