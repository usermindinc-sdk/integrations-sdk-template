/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.JourneystatesRecord;
import java.util.Arrays;
import java.util.List;
import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class Journeystates
extends TableImpl<JourneystatesRecord> {
    private static final long serialVersionUID = -238227052L;
    public static final Journeystates JOURNEYSTATES = new Journeystates();
    public final TableField<JourneystatesRecord, Long> JOURNEY_STATE_ID;
    public final TableField<JourneystatesRecord, String> JOURNEY_STATE_NAME;

    @Override
    public Class<JourneystatesRecord> getRecordType() {
        return JourneystatesRecord.class;
    }

    public Journeystates() {
        this("JourneyStates", null);
    }

    public Journeystates(String alias) {
        this(alias, JOURNEYSTATES);
    }

    private Journeystates(String alias, Table<JourneystatesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Journeystates(String alias, Table<JourneystatesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.JOURNEY_STATE_ID = Journeystates.createField("journey_state_id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"JourneyStates_journey_state_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.JOURNEY_STATE_NAME = Journeystates.createField("journey_state_name", SQLDataType.CLOB.nullable(false), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<JourneystatesRecord, Long> getIdentity() {
        return Keys.IDENTITY_JOURNEYSTATES;
    }

    @Override
    public UniqueKey<JourneystatesRecord> getPrimaryKey() {
        return Keys.PK_JOURNEYSTATES;
    }

    @Override
    public List<UniqueKey<JourneystatesRecord>> getKeys() {
        return Arrays.asList(Keys.PK_JOURNEYSTATES, Keys.JOURNEYSTATES_JOURNEY_STATE_NAME_KEY);
    }

    public Journeystates as(String alias) {
        return new Journeystates(alias, this);
    }

    public Journeystates rename(String name) {
        return new Journeystates(name, null);
    }
}

