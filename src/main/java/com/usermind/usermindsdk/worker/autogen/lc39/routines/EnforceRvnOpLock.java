/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.routines;

import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import org.jooq.DataType;
import org.jooq.Parameter;
import org.jooq.Schema;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.DefaultDataType;

public class EnforceRvnOpLock
extends AbstractRoutine<Object> {
    private static final long serialVersionUID = 2066226740L;
    public static final Parameter<Object> RETURN_VALUE = EnforceRvnOpLock.createParameter("RETURN_VALUE", DefaultDataType.getDefaultDataType("trigger"), false, false);

    public EnforceRvnOpLock() {
        super("enforce_rvn_op_lock", (Schema)Public.PUBLIC, DefaultDataType.getDefaultDataType("trigger"));
        this.setReturnParameter(RETURN_VALUE);
    }
}

