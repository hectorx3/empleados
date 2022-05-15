/*
 * This file is generated by jOOQ.
 */
package schemas.tables.records;


import java.math.BigDecimal;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

import schemas.tables.Jobs;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JobsRecord extends UpdatableRecordImpl<JobsRecord> implements Record3<Integer, String, BigDecimal> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.jobs.id</code>.
     */
    public JobsRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.jobs.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.jobs.name</code>.
     */
    public JobsRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.jobs.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.jobs.salary</code>.
     */
    public JobsRecord setSalary(BigDecimal value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.jobs.salary</code>.
     */
    public BigDecimal getSalary() {
        return (BigDecimal) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, BigDecimal> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, String, BigDecimal> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Jobs.JOBS.ID;
    }

    @Override
    public Field<String> field2() {
        return Jobs.JOBS.NAME;
    }

    @Override
    public Field<BigDecimal> field3() {
        return Jobs.JOBS.SALARY;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public BigDecimal component3() {
        return getSalary();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public BigDecimal value3() {
        return getSalary();
    }

    @Override
    public JobsRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public JobsRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public JobsRecord value3(BigDecimal value) {
        setSalary(value);
        return this;
    }

    @Override
    public JobsRecord values(Integer value1, String value2, BigDecimal value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached JobsRecord
     */
    public JobsRecord() {
        super(Jobs.JOBS);
    }

    /**
     * Create a detached, initialised JobsRecord
     */
    public JobsRecord(Integer id, String name, BigDecimal salary) {
        super(Jobs.JOBS);

        setId(id);
        setName(name);
        setSalary(salary);
    }

    /**
     * Create a detached, initialised JobsRecord
     */
    public JobsRecord(schemas.tables.pojos.Jobs value) {
        super(Jobs.JOBS);

        if (value != null) {
            setId(value.getId());
            setName(value.getName());
            setSalary(value.getSalary());
        }
    }
}