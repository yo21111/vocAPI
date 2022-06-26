package com.tf.cs.voc.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tf.cs.voc.domain.Compensation;
import com.tf.cs.voc.domain.QCompensation;
import com.tf.cs.voc.dto.CompensationDto;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class CompensationRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public CompensationRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Compensation.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }


    public List<Compensation> findCompList() {
        QCompensation qComp = new QCompensation("compensation");
        return jpaQueryFactory
                .select(qComp)
                .from(qComp)
                .orderBy(qComp.register_date.desc())
                .fetch();
    }

    public List<Compensation> findCompList_Penalty() {
        QCompensation qComp = new QCompensation("compensation");
        return jpaQueryFactory
                .select(qComp)
                .from(qComp)
                .where(qComp.penalty.penalty_price.gt(0))
                .orderBy(qComp.register_date.desc())
                .fetch();
    }

    public List<Compensation> findCompList_NoPenalty() {
        QCompensation qComp = new QCompensation("compensation");
        return jpaQueryFactory
                .select(qComp)
                .from(qComp)
                .where(qComp.penalty.penalty_price.eq(0))
                .orderBy(qComp.register_date.desc())
                .fetch();
    }
}
