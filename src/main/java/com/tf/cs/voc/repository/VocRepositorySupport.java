package com.tf.cs.voc.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tf.cs.voc.domain.Compensation;
import com.tf.cs.voc.domain.QVOC;
import com.tf.cs.voc.domain.VOC;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VocRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public VocRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Compensation.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }


    public List<VOC> findVocList_uncheck() {
        QVOC qvoc = new QVOC("vOC");
        return jpaQueryFactory
                .select(qvoc)
                .from(qvoc)
                .where(
                        qvoc.objection.confirm_date.isNull()
                )
                .orderBy(qvoc.compensation.register_date.desc())
                .fetch();
    }

    public List<VOC> findVocList_check_objection() {
        QVOC qvoc = new QVOC("vOC");
        return jpaQueryFactory
                .select(qvoc)
                .from(qvoc)
                .where(
                        qvoc.objection.confirm_date.isNotNull().and(
                                qvoc.objection.objection_content.isNotNull()
                        )
                )
                .orderBy(qvoc.compensation.register_date.desc())
                .fetch();
    }

    public List<VOC> findVocList_check_noObjection() {
        QVOC qvoc = new QVOC("vOC");
        return jpaQueryFactory
                .select(qvoc)
                .from(qvoc)
                .where(
                        qvoc.objection.confirm_date.isNotNull().and(
                                qvoc.objection.objection_content.isNull()
                        )
                )
                .orderBy(qvoc.compensation.register_date.desc())
                .fetch();
    }
}
