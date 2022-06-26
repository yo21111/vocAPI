package com.tf.cs.voc.service;

import com.tf.cs.voc.domain.Compensation;

import java.util.List;

public interface CompService {
    List<Compensation> findCompList();

    List<Compensation> findCompList_Penalty();

    List<Compensation> findCompList_NoPenalty();
}
