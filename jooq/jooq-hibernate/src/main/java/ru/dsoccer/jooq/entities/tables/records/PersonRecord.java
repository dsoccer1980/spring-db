/*
 * This file is generated by jOOQ.
 */
package ru.dsoccer.jooq.entities.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

import ru.dsoccer.jooq.entities.tables.Person;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PersonRecord extends UpdatableRecordImpl<PersonRecord> implements Record3<Long, String, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>PERSON.ID</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>PERSON.ID</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>PERSON.PERSON_NAME</code>.
     */
    public void setPersonName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>PERSON.PERSON_NAME</code>.
     */
    public String getPersonName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>PERSON.COMPANY_ID</code>.
     */
    public void setCompanyId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>PERSON.COMPANY_ID</code>.
     */
    public Long getCompanyId() {
        return (Long) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, String, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Long, String, Long> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Person.PERSON.ID;
    }

    @Override
    public Field<String> field2() {
        return Person.PERSON.PERSON_NAME;
    }

    @Override
    public Field<Long> field3() {
        return Person.PERSON.COMPANY_ID;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getPersonName();
    }

    @Override
    public Long component3() {
        return getCompanyId();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getPersonName();
    }

    @Override
    public Long value3() {
        return getCompanyId();
    }

    @Override
    public PersonRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public PersonRecord value2(String value) {
        setPersonName(value);
        return this;
    }

    @Override
    public PersonRecord value3(Long value) {
        setCompanyId(value);
        return this;
    }

    @Override
    public PersonRecord values(Long value1, String value2, Long value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PersonRecord
     */
    public PersonRecord() {
        super(Person.PERSON);
    }

    /**
     * Create a detached, initialised PersonRecord
     */
    public PersonRecord(Long id, String personName, Long companyId) {
        super(Person.PERSON);

        setId(id);
        setPersonName(personName);
        setCompanyId(companyId);
    }
}