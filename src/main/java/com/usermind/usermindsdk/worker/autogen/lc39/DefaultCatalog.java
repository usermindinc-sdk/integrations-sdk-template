/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39;

import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.jooq.Schema;
import org.jooq.impl.CatalogImpl;

public class DefaultCatalog
extends CatalogImpl {
    private static final long serialVersionUID = 413389756L;
    public static final DefaultCatalog DEFAULT_CATALOG = new DefaultCatalog();
    public final Public PUBLIC = Public.PUBLIC;

    private DefaultCatalog() {
        super("");
    }

    @Override
    public final List<Schema> getSchemas() {
        ArrayList<Schema> result = new ArrayList<Schema>();
        result.addAll(this.getSchemas0());
        return result;
    }

    private final List<Schema> getSchemas0() {
        return Arrays.asList(Public.PUBLIC);
    }
}

