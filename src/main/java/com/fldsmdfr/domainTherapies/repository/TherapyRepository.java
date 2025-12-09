package com.fldsmdfr.domainTherapies.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fldsmdfr.domainTherapies.models.Therapy;
import com.fldsmdfr.domainTherapies.models.CustomModels.TherapyNameOnly;
import com.fldsmdfr.domainTherapies.models.utilities.TherapyStatus;
import com.fldsmdfr.common.repository.GenericRepository;



public interface TherapyRepository extends GenericRepository<Therapy, Long> {
    Optional<Therapy> findByNameIgnoreCase(String name);

    List<TherapyNameOnly> findByStatusNotAndNameIgnoreCaseContains(TherapyStatus status, String name, Pageable pageable);

    Page<Therapy> findByStatusNot(TherapyStatus status,Pageable pageable);
    
    @Override
    default Page<Therapy> findAll(Pageable pageable) {
        return findByStatusNot(TherapyStatus.DELETED, pageable);    
    }

    Page<Therapy> findByNameIgnoreCaseContainsAndStatusNot(TherapyStatus status, String name, Pageable pageable);

    default Page<Therapy> findByName(String name, Pageable pageable){
        return findByNameIgnoreCaseContainsAndStatusNot(TherapyStatus.DELETED, name, pageable);
    }
}
