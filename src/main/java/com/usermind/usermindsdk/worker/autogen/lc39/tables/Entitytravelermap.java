/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.EntitytravelermapRecord;
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

public class Entitytravelermap
extends TableImpl<EntitytravelermapRecord> {
    private static final long serialVersionUID = -1466297690L;
    public static final Entitytravelermap ENTITYTRAVELERMAP = new Entitytravelermap();
    public final TableField<EntitytravelermapRecord, Long> ID;
    public final TableField<EntitytravelermapRecord, Long> JOURNEY_ID;
    public final TableField<EntitytravelermapRecord, String> CHANNEL_NAME;
    public final TableField<EntitytravelermapRecord, String> ENTITY_TYPE;
    public final TableField<EntitytravelermapRecord, String> ENTITY_ID;
    public final TableField<EntitytravelermapRecord, String> TRAVELER_IDS;

    @Override
    public Class<EntitytravelermapRecord> getRecordType() {
        return EntitytravelermapRecord.class;
    }

    public Entitytravelermap() {
        this("EntityTravelerMap", null);
    }

    public Entitytravelermap(String alias) {
        this(alias, ENTITYTRAVELERMAP);
    }

    private Entitytravelermap(String alias, Table<EntitytravelermapRecord> aliased) {
        this(alias, aliased, null);
    }

    private Entitytravelermap(String alias, Table<EntitytravelermapRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Entitytravelermap.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"EntityTravelerMap_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.JOURNEY_ID = Entitytravelermap.createField("journey_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.CHANNEL_NAME = Entitytravelermap.createField("channel_name", SQLDataType.VARCHAR.length(255).nullable(false), this, "");
        this.ENTITY_TYPE = Entitytravelermap.createField("entity_type", SQLDataType.VARCHAR.length(255).nullable(false), this, "");
        this.ENTITY_ID = Entitytravelermap.createField("entity_id", SQLDataType.VARCHAR.length(255).nullable(false), this, "");
        this.TRAVELER_IDS = Entitytravelermap.createField("traveler_ids", SQLDataType.CLOB, this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<EntitytravelermapRecord, Long> getIdentity() {
        return Keys.IDENTITY_ENTITYTRAVELERMAP;
    }

    @Override
    public UniqueKey<EntitytravelermapRecord> getPrimaryKey() {
        return Keys.ENTITYTRAVELERMAP_PKEY;
    }

    @Override
    public List<UniqueKey<EntitytravelermapRecord>> getKeys() {
        return Arrays.asList(Keys.ENTITYTRAVELERMAP_PKEY);
    }

    public Entitytravelermap as(String alias) {
        return new Entitytravelermap(alias, this);
    }

    public Entitytravelermap rename(String name) {
        return new Entitytravelermap(name, null);
    }
}

