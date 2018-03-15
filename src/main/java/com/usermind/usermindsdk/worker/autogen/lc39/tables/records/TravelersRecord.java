/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Travelers;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record21;
import org.jooq.Row;
import org.jooq.Row21;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.TableRecordImpl;

public class TravelersRecord
extends TableRecordImpl<TravelersRecord>
implements Record21<Long, Long, String, Timestamp, Timestamp, String, Timestamp, Timestamp, Long, String, String, String, String, String, String, Long, Long, String, String, String, String> {
    private static final long serialVersionUID = -240125288L;

    public void setId(Long value) {
        this.set(0, value);
    }

    public Long getId() {
        return (Long)this.get(0);
    }

    public void setOrganizationId(Long value) {
        this.set(1, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(1);
    }

    public void setName(String value) {
        this.set(2, value);
    }

    public String getName() {
        return (String)this.get(2);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(3, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(3);
    }

    public void setUpdatedAt(Timestamp value) {
        this.set(4, value);
    }

    public Timestamp getUpdatedAt() {
        return (Timestamp)this.get(4);
    }

    public void setEmail(String value) {
        this.set(5, value);
    }

    public String getEmail() {
        return (String)this.get(5);
    }

    public void setFinishDt(Timestamp value) {
        this.set(6, value);
    }

    public Timestamp getFinishDt() {
        return (Timestamp)this.get(6);
    }

    public void setStartDt(Timestamp value) {
        this.set(7, value);
    }

    public Timestamp getStartDt() {
        return (Timestamp)this.get(7);
    }

    public void setJourneyId(Long value) {
        this.set(8, value);
    }

    public Long getJourneyId() {
        return (Long)this.get(8);
    }

    public void setVarA(String value) {
        this.set(9, value);
    }

    public String getVarA() {
        return (String)this.get(9);
    }

    public void setVarB(String value) {
        this.set(10, value);
    }

    public String getVarB() {
        return (String)this.get(10);
    }

    public void setVarC(String value) {
        this.set(11, value);
    }

    public String getVarC() {
        return (String)this.get(11);
    }

    public void setVarD(String value) {
        this.set(12, value);
    }

    public String getVarD() {
        return (String)this.get(12);
    }

    public void setVarE(String value) {
        this.set(13, value);
    }

    public String getVarE() {
        return (String)this.get(13);
    }

    public void setVarF(String value) {
        this.set(14, value);
    }

    public String getVarF() {
        return (String)this.get(14);
    }

    public void setEntitygraphId(Long value) {
        this.set(15, value);
    }

    public Long getEntitygraphId() {
        return (Long)this.get(15);
    }

    public void setVersion(Long value) {
        this.set(16, value);
    }

    public Long getVersion() {
        return (Long)this.get(16);
    }

    public void setVarG(String value) {
        this.set(17, value);
    }

    public String getVarG() {
        return (String)this.get(17);
    }

    public void setVarH(String value) {
        this.set(18, value);
    }

    public String getVarH() {
        return (String)this.get(18);
    }

    public void setVarI(String value) {
        this.set(19, value);
    }

    public String getVarI() {
        return (String)this.get(19);
    }

    public void setVarJ(String value) {
        this.set(20, value);
    }

    public String getVarJ() {
        return (String)this.get(20);
    }

    @Override
    public Row21<Long, Long, String, Timestamp, Timestamp, String, Timestamp, Timestamp, Long, String, String, String, String, String, String, Long, Long, String, String, String, String> fieldsRow() {
        return (Row21)super.fieldsRow();
    }

    @Override
    public Row21<Long, Long, String, Timestamp, Timestamp, String, Timestamp, Timestamp, Long, String, String, String, String, String, String, Long, Long, String, String, String, String> valuesRow() {
        return (Row21)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Travelers.TRAVELERS.ID;
    }

    @Override
    public Field<Long> field2() {
        return Travelers.TRAVELERS.ORGANIZATION_ID;
    }

    @Override
    public Field<String> field3() {
        return Travelers.TRAVELERS.NAME;
    }

    @Override
    public Field<Timestamp> field4() {
        return Travelers.TRAVELERS.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field5() {
        return Travelers.TRAVELERS.UPDATED_AT;
    }

    @Override
    public Field<String> field6() {
        return Travelers.TRAVELERS.EMAIL;
    }

    @Override
    public Field<Timestamp> field7() {
        return Travelers.TRAVELERS.FINISH_DT;
    }

    @Override
    public Field<Timestamp> field8() {
        return Travelers.TRAVELERS.START_DT;
    }

    @Override
    public Field<Long> field9() {
        return Travelers.TRAVELERS.JOURNEY_ID;
    }

    @Override
    public Field<String> field10() {
        return Travelers.TRAVELERS.VAR_A;
    }

    @Override
    public Field<String> field11() {
        return Travelers.TRAVELERS.VAR_B;
    }

    @Override
    public Field<String> field12() {
        return Travelers.TRAVELERS.VAR_C;
    }

    @Override
    public Field<String> field13() {
        return Travelers.TRAVELERS.VAR_D;
    }

    @Override
    public Field<String> field14() {
        return Travelers.TRAVELERS.VAR_E;
    }

    @Override
    public Field<String> field15() {
        return Travelers.TRAVELERS.VAR_F;
    }

    @Override
    public Field<Long> field16() {
        return Travelers.TRAVELERS.ENTITYGRAPH_ID;
    }

    @Override
    public Field<Long> field17() {
        return Travelers.TRAVELERS.VERSION;
    }

    @Override
    public Field<String> field18() {
        return Travelers.TRAVELERS.VAR_G;
    }

    @Override
    public Field<String> field19() {
        return Travelers.TRAVELERS.VAR_H;
    }

    @Override
    public Field<String> field20() {
        return Travelers.TRAVELERS.VAR_I;
    }

    @Override
    public Field<String> field21() {
        return Travelers.TRAVELERS.VAR_J;
    }

    @Override
    public Long value1() {
        return this.getId();
    }

    @Override
    public Long value2() {
        return this.getOrganizationId();
    }

    @Override
    public String value3() {
        return this.getName();
    }

    @Override
    public Timestamp value4() {
        return this.getCreatedAt();
    }

    @Override
    public Timestamp value5() {
        return this.getUpdatedAt();
    }

    @Override
    public String value6() {
        return this.getEmail();
    }

    @Override
    public Timestamp value7() {
        return this.getFinishDt();
    }

    @Override
    public Timestamp value8() {
        return this.getStartDt();
    }

    @Override
    public Long value9() {
        return this.getJourneyId();
    }

    @Override
    public String value10() {
        return this.getVarA();
    }

    @Override
    public String value11() {
        return this.getVarB();
    }

    @Override
    public String value12() {
        return this.getVarC();
    }

    @Override
    public String value13() {
        return this.getVarD();
    }

    @Override
    public String value14() {
        return this.getVarE();
    }

    @Override
    public String value15() {
        return this.getVarF();
    }

    @Override
    public Long value16() {
        return this.getEntitygraphId();
    }

    @Override
    public Long value17() {
        return this.getVersion();
    }

    @Override
    public String value18() {
        return this.getVarG();
    }

    @Override
    public String value19() {
        return this.getVarH();
    }

    @Override
    public String value20() {
        return this.getVarI();
    }

    @Override
    public String value21() {
        return this.getVarJ();
    }

    public TravelersRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public TravelersRecord value2(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public TravelersRecord value3(String value) {
        this.setName(value);
        return this;
    }

    public TravelersRecord value4(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public TravelersRecord value5(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public TravelersRecord value6(String value) {
        this.setEmail(value);
        return this;
    }

    public TravelersRecord value7(Timestamp value) {
        this.setFinishDt(value);
        return this;
    }

    public TravelersRecord value8(Timestamp value) {
        this.setStartDt(value);
        return this;
    }

    public TravelersRecord value9(Long value) {
        this.setJourneyId(value);
        return this;
    }

    public TravelersRecord value10(String value) {
        this.setVarA(value);
        return this;
    }

    public TravelersRecord value11(String value) {
        this.setVarB(value);
        return this;
    }

    public TravelersRecord value12(String value) {
        this.setVarC(value);
        return this;
    }

    public TravelersRecord value13(String value) {
        this.setVarD(value);
        return this;
    }

    public TravelersRecord value14(String value) {
        this.setVarE(value);
        return this;
    }

    public TravelersRecord value15(String value) {
        this.setVarF(value);
        return this;
    }

    public TravelersRecord value16(Long value) {
        this.setEntitygraphId(value);
        return this;
    }

    public TravelersRecord value17(Long value) {
        this.setVersion(value);
        return this;
    }

    public TravelersRecord value18(String value) {
        this.setVarG(value);
        return this;
    }

    public TravelersRecord value19(String value) {
        this.setVarH(value);
        return this;
    }

    public TravelersRecord value20(String value) {
        this.setVarI(value);
        return this;
    }

    public TravelersRecord value21(String value) {
        this.setVarJ(value);
        return this;
    }

    public TravelersRecord values(Long value1, Long value2, String value3, Timestamp value4, Timestamp value5, String value6, Timestamp value7, Timestamp value8, Long value9, String value10, String value11, String value12, String value13, String value14, String value15, Long value16, Long value17, String value18, String value19, String value20, String value21) {
        this.value1(value1);
        this.value2(value2);
        this.value3(value3);
        this.value4(value4);
        this.value5(value5);
        this.value6(value6);
        this.value7(value7);
        this.value8(value8);
        this.value9(value9);
        this.value10(value10);
        this.value11(value11);
        this.value12(value12);
        this.value13(value13);
        this.value14(value14);
        this.value15(value15);
        this.value16(value16);
        this.value17(value17);
        this.value18(value18);
        this.value19(value19);
        this.value20(value20);
        this.value21(value21);
        return this;
    }

    public TravelersRecord() {
        super(Travelers.TRAVELERS);
    }

    public TravelersRecord(Long id, Long organizationId, String name, Timestamp createdAt, Timestamp updatedAt, String email, Timestamp finishDt, Timestamp startDt, Long journeyId, String varA, String varB, String varC, String varD, String varE, String varF, Long entitygraphId, Long version, String varG, String varH, String varI, String varJ) {
        super(Travelers.TRAVELERS);
        this.set(0, id);
        this.set(1, organizationId);
        this.set(2, name);
        this.set(3, createdAt);
        this.set(4, updatedAt);
        this.set(5, email);
        this.set(6, finishDt);
        this.set(7, startDt);
        this.set(8, journeyId);
        this.set(9, varA);
        this.set(10, varB);
        this.set(11, varC);
        this.set(12, varD);
        this.set(13, varE);
        this.set(14, varF);
        this.set(15, entitygraphId);
        this.set(16, version);
        this.set(17, varG);
        this.set(18, varH);
        this.set(19, varI);
        this.set(20, varJ);
    }
}

