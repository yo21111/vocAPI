package com.tf.cs.voc.repository;

import com.tf.cs.voc.domain.VOC;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VocRepository extends JpaRepository<VOC, Long> {
    List<VOC> findAll(Specification<VOC> equalClassification, Sort id);
}
