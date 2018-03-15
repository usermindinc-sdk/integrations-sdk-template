/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.MapsRecord;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.ProctriggersRecord;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class Proctriggers
extends TableImpl<ProctriggersRecord> {
    private static final long serialVersionUID = 1351694079L;
    public static final Proctriggers PROCTRIGGERS = new Proctriggers();
    public final TableField<ProctriggersRecord, Long> ID;
    public final TableField<ProctriggersRecord, String> TYPE;
    public final TableField<ProctriggersRecord, Long> ORGANIZATION_ID;
    public final TableField<ProctriggersRecord, Long> TIMESTAMP;
    public final TableField<ProctriggersRecord, Long> MAP_SPEC_ID;
    public final TableField<ProctriggersRecord, Long> JOURNEY_ID;
    public final TableField<ProctriggersRecord, Long> PARENT_ID;
    public final TableField<ProctriggersRecord, Integer> PROC_STATE;
    public final TableField<ProctriggersRecord, Timestamp> PROCESSING_STARTED;
    public final TableField<ProctriggersRecord, Timestamp> PROCESSING_FINISHED;
    public final TableField<ProctriggersRecord, Object> CHECKPOINT_DATA;
    public final TableField<ProctriggersRecord, Timestamp> CREATED_AT;
    public final TableField<ProctriggersRecord, Timestamp> UPDATED_AT;

    @Override
    public Class<ProctriggersRecord> getRecordType() {
        return ProctriggersRecord.class;
    }

    public Proctriggers() {
        this("ProcTriggers", null);
    }

    public Proctriggers(String alias) {
        this(alias, PROCTRIGGERS);
    }

    private Proctriggers(String alias, Table<ProctriggersRecord> aliased) {
        this(alias, aliased, null);
    }

    private Proctriggers(String alias, Table<ProctriggersRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Proctriggers.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"ProcTriggers_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.TYPE = Proctriggers.createField("type", SQLDataType.VARCHAR.length(255).nullable(false), this, "");
        this.ORGANIZATION_ID = Proctriggers.createField("organization_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.TIMESTAMP = Proctriggers.createField("timestamp", SQLDataType.BIGINT, this, "");
        this.MAP_SPEC_ID = Proctriggers.createField("map_spec_id", SQLDataType.BIGINT, this, "");
        this.JOURNEY_ID = Proctriggers.createField("journey_id", SQLDataType.BIGINT, this, "");
        this.PARENT_ID = Proctriggers.createField("parent_id", SQLDataType.BIGINT, this, "");
        this.PROC_STATE = Proctriggers.createField("proc_state", SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");
        this.PROCESSING_STARTED = Proctriggers.createField("processing_started", SQLDataType.TIMESTAMP, this, "");
        this.PROCESSING_FINISHED = Proctriggers.createField("processing_finished", SQLDataType.TIMESTAMP, this, "");
        this.CHECKPOINT_DATA = Proctriggers.createField("checkpoint_data", SQLDataType.OTHER, this, "");
        this.CREATED_AT = Proctriggers.createField("created_at", SQLDataType.TIMESTAMP.defaultValue(DSL.field("now()", SQLDataType.TIMESTAMP)), this, "");
        this.UPDATED_AT = Proctriggers.createField("updated_at", SQLDataType.TIMESTAMP.defaultValue(DSL.field("now()", SQLDataType.TIMESTAMP)), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<ProctriggersRecord, Long> getIdentity() {
        return Keys.IDENTITY_PROCTRIGGERS;
    }

    @Override
    public List<ForeignKey<ProctriggersRecord, ?>> getReferences() {
        return Arrays.asList(Keys.PROCTRIGGERS__FK_PROCTRIGGERS_MAP_SPEC);
    }

    public Proctriggers as(String alias) {
        return new Proctriggers(alias, this);
    }

    public Proctriggers rename(String name) {
        return new Proctriggers(name, null);
    }
}

