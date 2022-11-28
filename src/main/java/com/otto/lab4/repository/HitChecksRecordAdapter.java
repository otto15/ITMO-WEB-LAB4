package com.otto.lab4.repository;

import com.otto.lab4.domain.HitCheck;
import com.otto.lab4.jooq.tables.records.HitChecksRecord;
import org.jooq.RecordMapper;
import org.jooq.RecordUnmapper;
import org.jooq.exception.MappingException;
import org.springframework.stereotype.Component;

@Component
public class HitChecksRecordAdapter implements RecordMapper<HitChecksRecord, HitCheck>,
        RecordUnmapper<HitCheck, HitChecksRecord> {
    @Override
    public HitCheck map(HitChecksRecord hitChecksRecord) {
        //TODO mapper
        return null;
    }


    @Override
    public HitChecksRecord unmap(HitCheck hitCheck) throws MappingException {
        //TODO unmapper
        return null;
    }
}
