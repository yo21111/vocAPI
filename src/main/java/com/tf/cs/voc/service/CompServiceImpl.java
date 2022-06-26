package com.tf.cs.voc.service;

import com.tf.cs.voc.domain.Compensation;
import com.tf.cs.voc.repository.CompensationRepository;
import com.tf.cs.voc.repository.CompensationRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompServiceImpl implements CompService {
    private final CompensationRepository repository;
    private final CompensationRepositorySupport support;

    @Override
    public List<Compensation> findCompList() {
        return support.findCompList();
    }

    @Override
    public List<Compensation> findCompList_Penalty() {
        return support.findCompList_Penalty();
    }

    @Override
    public List<Compensation> findCompList_NoPenalty() {
        return support.findCompList_NoPenalty();
    }

}
