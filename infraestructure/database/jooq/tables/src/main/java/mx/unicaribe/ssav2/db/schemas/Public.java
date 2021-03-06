/*
 * This file is generated by jOOQ.
 */
package schemas;


import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import schemas.tables.Employess;
import schemas.tables.EmployessWorked;
import schemas.tables.FlywaySchemaHistory;
import schemas.tables.Genders;
import schemas.tables.Jobs;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.employess</code>.
     */
    public final Employess EMPLOYESS = Employess.EMPLOYESS;

    /**
     * The table <code>public.employess_worked</code>.
     */
    public final EmployessWorked EMPLOYESS_WORKED = EmployessWorked.EMPLOYESS_WORKED;

    /**
     * The table <code>public.flyway_schema_history</code>.
     */
    public final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * The table <code>public.genders</code>.
     */
    public final Genders GENDERS = Genders.GENDERS;

    /**
     * The table <code>public.jobs</code>.
     */
    public final Jobs JOBS = Jobs.JOBS;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Employess.EMPLOYESS,
            EmployessWorked.EMPLOYESS_WORKED,
            FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY,
            Genders.GENDERS,
            Jobs.JOBS
        );
    }
}
