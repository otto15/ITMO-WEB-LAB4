package com.otto.lab4.repository;

import com.otto.lab4.domain.HitCheck;
import com.otto.lab4.jooq.tables.records.HitChecksRecord;
import org.jooq.RecordMapper;
import org.jooq.RecordUnmapper;
import org.jooq.exception.MappingException;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;

@Component
public class HitChecksRecordAdapter implements RecordMapper<HitChecksRecord, HitCheck>,
        RecordUnmapper<HitCheck, HitChecksRecord> {
    @Override
    public HitCheck map(HitChecksRecord hitChecksRecord) {
        return HitCheck.builder()
                .x(hitChecksRecord.getX())
                .y(hitChecksRecord.getY())
                .r(hitChecksRecord.getR())
                .callingDate(hitChecksRecord.getCallingDate().toInstant())
                .executionTime(hitChecksRecord.getExecutionTime())
                .hitStatus(hitChecksRecord.getHitStatus())
                .build();
    }


    @Override
    public HitChecksRecord unmap(HitCheck hitCheck) throws MappingException {
        HitChecksRecord record = new HitChecksRecord();

        record.setUserId(hitCheck.getUserId());
        record.setX(hitCheck.getX());
        record.setY(hitCheck.getY());
        record.setR(hitCheck.getR());
        record.setCallingDate(hitCheck.getCallingDate().atOffset(ZoneOffset.UTC));
        record.setExecutionTime(hitCheck.getExecutionTime());
        record.setHitStatus(hitCheck.getHitStatus());

        return record;
    }
}
