package org.codemental.samples.webflux.java.jooq.notx.user.dao;

import org.codemental.samples.webflux.java.jooq.generated.tables.pojos.ImUser;
import org.codemental.samples.webflux.java.jooq.generated.tables.records.ImUserRecord;
import org.jooq.DSLContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.codemental.samples.webflux.java.jooq.generated.Tables.IM_USER;


public class UserDao {

    private final DSLContext jooq;

    private static final String LAST_NAME_JSON_NAME = "lastName";
    private static final String FIRST_NAME_JSON_NAME = "firstName";


    public UserDao(DSLContext dslContext) {
        this.jooq = dslContext;
    }

    public Optional<Map<String, Object>> fetchOneMapById(int id) {
        return jooq.select(IM_USER.ID, IM_USER.MODIFIED,
                IM_USER.LAST_NAME.as(LAST_NAME_JSON_NAME),
                IM_USER.FIRST_NAME.as(FIRST_NAME_JSON_NAME),
                IM_USER.TITLE,
                IM_USER.EMAIL)
                .from(IM_USER)
                .where(IM_USER.ID.equal(id))
                .fetchOptionalMap();
    }

    public Optional<List<Map<String, Object>>> fetchMaps() {
        return Optional.of(jooq.select(IM_USER.ID,
                IM_USER.LAST_NAME.as(LAST_NAME_JSON_NAME),
                IM_USER.FIRST_NAME.as(FIRST_NAME_JSON_NAME),
                IM_USER.TITLE)
                .from(IM_USER)
                .fetchMaps());
    }

    public Integer insert(ImUser user) {
        return jooq.insertInto(IM_USER)
                .set(IM_USER.FIRST_NAME, user.getFirstName())
                .set(IM_USER.LAST_NAME, user.getLastName())
                .set(IM_USER.TITLE, user.getTitle())
                .set(IM_USER.EMAIL, user.getEmail())
                .returning(IM_USER.ID)
                .fetchOne()
                .getId();
    }

    public Optional<LocalDateTime> update(ImUser user) {
        ImUserRecord userRecord = jooq.newRecord(IM_USER, user);

        // .changed(..., false) - marks the field as unchanged -> jooq does not update it, but the value remains inside the record
        userRecord.changed(IM_USER.ID, false);
        userRecord.changed(IM_USER.CREATED, false);

        userRecord.update();

        return Optional.of(userRecord.getModified());
    }

    public Optional<Integer> delete(int id) {
        return Optional.of(jooq.deleteFrom(IM_USER)
                .where(IM_USER.ID.eq(id))
                .execute());
    }
}
