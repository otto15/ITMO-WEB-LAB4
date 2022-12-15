package com.otto.lab4.repository;

import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import static com.otto.lab4.jooq.Tables.RESTRICTED_REFRESH_TOKENS;

@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final DSLContext dsl;

    @Autowired
    public RefreshTokenRepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public boolean existByToken(String token) {
        return dsl.fetchExists(
                dsl.selectFrom(RESTRICTED_REFRESH_TOKENS)
                        .where(RESTRICTED_REFRESH_TOKENS.TOKEN.eq(token)));
    }

    @Override
    public Integer save(String token) {
        return dsl.insertInto(RESTRICTED_REFRESH_TOKENS)
                .set(RESTRICTED_REFRESH_TOKENS.TOKEN, token)
                .returning(RESTRICTED_REFRESH_TOKENS.ID)
                .fetchOptional()
                .orElseThrow(() -> new DataAccessException("Error inserting refresh token"))
                .get(RESTRICTED_REFRESH_TOKENS.ID);
    }
}