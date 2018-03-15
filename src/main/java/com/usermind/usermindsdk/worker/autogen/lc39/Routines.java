/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39;

import com.usermind.usermindsdk.worker.autogen.lc39.routines.EnforceRvnOpLock;
import com.usermind.usermindsdk.worker.autogen.lc39.routines.FaqwadMsgEnforceInsertConstraints;
import com.usermind.usermindsdk.worker.autogen.lc39.routines.TeEnforceInsertConstraints;
import com.usermind.usermindsdk.worker.autogen.lc39.routines.UpdateTxnTimes;
import org.jooq.Configuration;
import org.jooq.Field;

public class Routines {
    public static Object enforceRvnOpLock(Configuration configuration) {
        EnforceRvnOpLock f = new EnforceRvnOpLock();
        f.execute(configuration);
        return f.getReturnValue();
    }

    public static Field<Object> enforceRvnOpLock() {
        EnforceRvnOpLock f = new EnforceRvnOpLock();
        return f.asField();
    }

    public static Object faqwadMsgEnforceInsertConstraints(Configuration configuration) {
        FaqwadMsgEnforceInsertConstraints f = new FaqwadMsgEnforceInsertConstraints();
        f.execute(configuration);
        return f.getReturnValue();
    }

    public static Field<Object> faqwadMsgEnforceInsertConstraints() {
        FaqwadMsgEnforceInsertConstraints f = new FaqwadMsgEnforceInsertConstraints();
        return f.asField();
    }

    public static Object teEnforceInsertConstraints(Configuration configuration) {
        TeEnforceInsertConstraints f = new TeEnforceInsertConstraints();
        f.execute(configuration);
        return f.getReturnValue();
    }

    public static Field<Object> teEnforceInsertConstraints() {
        TeEnforceInsertConstraints f = new TeEnforceInsertConstraints();
        return f.asField();
    }

    public static Object updateTxnTimes(Configuration configuration) {
        UpdateTxnTimes f = new UpdateTxnTimes();
        f.execute(configuration);
        return f.getReturnValue();
    }

    public static Field<Object> updateTxnTimes() {
        UpdateTxnTimes f = new UpdateTxnTimes();
        return f.asField();
    }
}

