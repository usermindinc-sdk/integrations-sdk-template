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

public class FaqwadMsgEnforceInsertConstraints
extends AbstractRoutine<Object> {
    private static final long serialVersionUID = -2105516550L;
    public static final Parameter<Object> RETURN_VALUE = FaqwadMsgEnforceInsertConstraints.createParameter("RETURN_VALUE", DefaultDataType.getDefaultDataType("trigger"), false, false);

    public FaqwadMsgEnforceInsertConstraints() {
        super("faqwad_msg_enforce_insert_constraints", (Schema)Public.PUBLIC, DefaultDataType.getDefaultDataType("trigger"));
        this.setReturnParameter(RETURN_VALUE);
    }
}

