/*
 * This file is generated by jOOQ.
 */
package schemas.tables.pojos;


import java.io.Serializable;
import java.time.LocalDate;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class EmployessWorked implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer   id;
    private final Integer   employeeId;
    private final Integer   workedHours;
    private final LocalDate workedDate;

    public EmployessWorked(EmployessWorked value) {
        this.id = value.id;
        this.employeeId = value.employeeId;
        this.workedHours = value.workedHours;
        this.workedDate = value.workedDate;
    }

    public EmployessWorked(
        Integer   id,
        Integer   employeeId,
        Integer   workedHours,
        LocalDate workedDate
    ) {
        this.id = id;
        this.employeeId = employeeId;
        this.workedHours = workedHours;
        this.workedDate = workedDate;
    }

    /**
     * Getter for <code>public.employess_worked.id</code>.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Getter for <code>public.employess_worked.employee_id</code>.
     */
    public Integer getEmployeeId() {
        return this.employeeId;
    }

    /**
     * Getter for <code>public.employess_worked.worked_hours</code>.
     */
    public Integer getWorkedHours() {
        return this.workedHours;
    }

    /**
     * Getter for <code>public.employess_worked.worked_date</code>.
     */
    public LocalDate getWorkedDate() {
        return this.workedDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("EmployessWorked (");

        sb.append(id);
        sb.append(", ").append(employeeId);
        sb.append(", ").append(workedHours);
        sb.append(", ").append(workedDate);

        sb.append(")");
        return sb.toString();
    }
}
