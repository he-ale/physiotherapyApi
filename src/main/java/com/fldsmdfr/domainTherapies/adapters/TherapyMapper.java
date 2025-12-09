package com.fldsmdfr.domainTherapies.adapters;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.fldsmdfr.domainTherapies.dto.dtoController.TherapyRegister;
import com.fldsmdfr.domainTherapies.dto.dtoController.TherapyResponse;
import com.fldsmdfr.domainTherapies.dto.dtoController.TherapyUpdate;
import com.fldsmdfr.domainTherapies.models.Therapy;
import com.fldsmdfr.domainTherapies.models.utilities.TherapyStatus;
import com.fldsmdfr.common.dto.PageResponse;


@Component
public class TherapyMapper {
    private final ModelMapper modelMapper;

    public TherapyMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.typeMap(TherapyUpdate.class, Therapy.class).addMappings(mapper -> {
            mapper.map(
                src -> src.getStatus() != null ? TherapyStatus.valueOf(src.getStatus()) : null,
                Therapy::setStatus
            );
        });
    }

    public Therapy toUpdateEntity(TherapyUpdate dto) {
        return modelMapper.map(dto, Therapy.class);
    }

    public TherapyResponse toResponse(Therapy therapy) {
        return modelMapper.map(therapy, TherapyResponse.class);
    }

    public Therapy toEntity(TherapyRegister therapyCreate) {
        return modelMapper.map(therapyCreate, Therapy.class);
    }

    public Therapy toEntity(TherapyResponse therapyResponse) {
        return modelMapper.map(therapyResponse, Therapy.class);
    }

    public PageResponse<TherapyResponse> toResponsePage(Page<Therapy> therapyPage) {
        PageResponse<TherapyResponse> responsePage = new PageResponse<>();
        responsePage.setTotalElements(therapyPage.getTotalElements());
        responsePage.setTotalPages(therapyPage.getTotalPages());
        responsePage.setPageSize(therapyPage.getSize());
        responsePage.setPageNumber(therapyPage.getNumber());
        responsePage.setLast(therapyPage.isLast());
        responsePage.setContent(therapyPage.map(this::toResponse).getContent());
        return responsePage;
    }
}
