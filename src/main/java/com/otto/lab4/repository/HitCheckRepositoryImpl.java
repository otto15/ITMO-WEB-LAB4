package com.otto.lab4.repository;

import com.otto.lab4.domain.HitCheck;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.otto.lab4.jooq.Tables.HIT_CHECKS;

@Repository
public class HitCheckRepositoryImpl implements HitCheckRepository {

    private final DSLContext dsl;
    private final HitChecksRecordAdapter hitChecksRecordAdapter;

    @Autowired
    public HitCheckRepositoryImpl(DSLContext context, HitChecksRecordAdapter hitChecksRecordAdapter) {
        this.dsl = context;
        this.hitChecksRecordAdapter = hitChecksRecordAdapter;
    }

    @Override
    public List<HitCheck> findAllByUserId(Integer userId) {
        return dsl.selectFrom(HIT_CHECKS)
                .where(HIT_CHECKS.USER_ID.eq(userId))
                .fetch(hitChecksRecordAdapter);
    }

    @Override
    public List<HitCheck> findAllByUserIdAndRadius(Integer userId, Double radius) {
        return dsl.selectFrom(HIT_CHECKS)
                .where(HIT_CHECKS.USER_ID.eq(userId).and(HIT_CHECKS.R.eq(radius)))
                .fetch(hitChecksRecordAdapter);
    }

    @Override
    public Integer save(HitCheck hitCheck) {
        return dsl.insertInto(HIT_CHECKS)
                .set(hitChecksRecordAdapter.unmap(hitCheck))
                .returning(HIT_CHECKS.ID)
                .fetchOptional()
                .orElseThrow(() -> new DataAccessException("Error inserting hit check"))
                .get(HIT_CHECKS.ID);
    }

    @Override
    public void deleteAllByUserId(Integer userId) {
        dsl.delete(HIT_CHECKS)
                .where(HIT_CHECKS.USER_ID.eq(userId))
                .execute();
    }
}
