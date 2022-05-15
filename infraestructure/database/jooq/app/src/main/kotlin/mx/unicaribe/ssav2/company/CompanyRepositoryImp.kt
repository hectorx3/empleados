package mx.unicaribe.ssav2.company

import com.zaxxer.hikari.HikariDataSource
import jakarta.inject.Inject
import jakarta.inject.Singleton
import mx.edu.ucaribe.ssav2.company.models.*
import mx.edu.ucaribe.ssav2.company.repositories.CompanyRepository
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import schemas.tables.Employess
import schemas.tables.EmployessWorked
import schemas.tables.Genders
import schemas.tables.Jobs
import java.time.LocalDate

@Singleton
class CompanyRepositoryImp
@Inject constructor(
     private val ds: HikariDataSource
 ) : CompanyRepository {

    override fun findAllGenders(): List<Genero> {
        ds.connection.use { connection ->
            val ctx = DSL.using(connection, SQLDialect.POSTGRES)
            val query = ctx.select(
                Genders.GENDERS.ID,
                Genders.GENDERS.NAME
            ).from(Genders.GENDERS)
                .fetch{ r ->
                    Genero(r.get(Genders.GENDERS.ID).toLong(), r.get(Genders.GENDERS.NAME))
                }
            return query
        }
    }

    override fun findAllJobs(): List<Trabajo> {
        ds.connection.use { connection ->
            val ctx = DSL.using(connection, SQLDialect.POSTGRES)
            val queryPart = ctx.select(
                Jobs.JOBS.ID,
                Jobs.JOBS.NAME,
                Jobs.JOBS.SALARY
            ).from(Jobs.JOBS)
                .where(DSL.trueCondition())

            val query = queryPart.fetch { r ->
                    Trabajo(r.get(Jobs.JOBS.ID).toLong(), r.get(Jobs.JOBS.NAME), r.get(Jobs.JOBS.SALARY))
                }
            return query
        }
    }

    override fun findAllEmployeesBy(
        idJob: Long?,
        name: String?,
        last_name: String?
    ): List<EmpleadoInformacion> {
        ds.connection.use { connection ->
            val ctx = DSL.using(connection, SQLDialect.POSTGRES)
            val queryPart = ctx.select(
                Employess.EMPLOYESS.ID,
                Employess.EMPLOYESS.GENDER_ID,
                Employess.EMPLOYESS.JOB_ID,
                Employess.EMPLOYESS.NAME,
                Employess.EMPLOYESS.LAST_NAME,
                Employess.EMPLOYESS.BIRTHDATE,
                Jobs.JOBS.ID,
                Jobs.JOBS.NAME,
                Jobs.JOBS.SALARY,
                Genders.GENDERS.ID,
                Genders.GENDERS.NAME
            ).from(Employess.EMPLOYESS)
                .join(Jobs.JOBS).on(Jobs.JOBS.ID.eq(Employess.EMPLOYESS.JOB_ID))
                .join(Genders.GENDERS).on(Genders.GENDERS.ID.eq(Employess.EMPLOYESS.GENDER_ID))
                .where(DSL.trueCondition())

            idJob?.let { queryPart.and(Employess.EMPLOYESS.JOB_ID.eq(it.toInt())) }
            name?.let { queryPart.and(Employess.EMPLOYESS.NAME.eq(it)) }
            last_name?.let { queryPart.and(Employess.EMPLOYESS.LAST_NAME.eq(it)) }

            val query = queryPart.fetch{ r ->
                    EmpleadoInformacion(
                        r.get(Employess.EMPLOYESS.ID).toLong(),
                        Genero(r.get(Genders.GENDERS.ID).toLong(), r.get(Genders.GENDERS.NAME)),
                        Trabajo(r.get(Jobs.JOBS.ID).toLong(), r.get(Jobs.JOBS.NAME), r.get(Jobs.JOBS.SALARY)),
                        r.get(Employess.EMPLOYESS.NAME),
                        r.get(Employess.EMPLOYESS.LAST_NAME),
                        r.get(Employess.EMPLOYESS.BIRTHDATE),
                    )
                }
            return query
        }
    }

    override fun saveEmployee(emp: Empleado): Long {
        ds.connection.use{connection ->
            val ctx = DSL.using(connection, SQLDialect.POSTGRES)
            val insert = ctx.insertInto(
                Employess.EMPLOYESS,
                Employess.EMPLOYESS.GENDER_ID,
                Employess.EMPLOYESS.JOB_ID,
                Employess.EMPLOYESS.NAME,
                Employess.EMPLOYESS.LAST_NAME,
                Employess.EMPLOYESS.BIRTHDATE
            )
                .values(emp.gender_id.toInt(), emp.job_id.toInt(), emp.name, emp.last_name, emp.birthdate)
                .returningResult(Employess.EMPLOYESS.ID)
                .fetchOne()

            return insert?.value1()?.toLong() ?: -1L
        }
    }

    override fun findAllWorkedHoursBy(
        idEmployee: Long?,
        workerDate: LocalDate?,
        startDate: LocalDate?,
        endDate: LocalDate?
    ): List<EmpHorTrabajo> {
        ds.connection.use { connection ->
            val ctx = DSL.using(connection, SQLDialect.POSTGRES)
            val queryPart = ctx.select(
                EmployessWorked.EMPLOYESS_WORKED.ID,
                EmployessWorked.EMPLOYESS_WORKED.EMPLOYEE_ID,
                EmployessWorked.EMPLOYESS_WORKED.WORKED_HOURS,
                EmployessWorked.EMPLOYESS_WORKED.WORKED_DATE,
                Jobs.JOBS.ID,
                Jobs.JOBS.NAME,
                Jobs.JOBS.SALARY,
            ).from(EmployessWorked.EMPLOYESS_WORKED)
                .join(Employess.EMPLOYESS).on(Employess.EMPLOYESS.ID.eq(EmployessWorked.EMPLOYESS_WORKED.EMPLOYEE_ID))
                .join(Jobs.JOBS).on(Jobs.JOBS.ID.eq(Employess.EMPLOYESS.JOB_ID))
                .where(DSL.trueCondition())

            idEmployee?.let { queryPart.and(EmployessWorked.EMPLOYESS_WORKED.EMPLOYEE_ID.eq(it.toInt())) }
            workerDate?.let { queryPart.and(EmployessWorked.EMPLOYESS_WORKED.WORKED_DATE.eq(it)) }
            if(startDate != null && endDate != null) {
                queryPart.and(EmployessWorked.EMPLOYESS_WORKED.WORKED_DATE.between(startDate, endDate) )
            }

            queryPart.orderBy(EmployessWorked.EMPLOYESS_WORKED.WORKED_DATE.desc())

            val query = queryPart.fetch { r ->
                EmpHorTrabajo(
                    Trabajo(r.get(Jobs.JOBS.ID).toLong(), r.get(Jobs.JOBS.NAME), r.get(Jobs.JOBS.SALARY)),
                    HoraTrabajo(
                        r.get(EmployessWorked.EMPLOYESS_WORKED.ID).toLong(),
                        r.get(EmployessWorked.EMPLOYESS_WORKED.EMPLOYEE_ID).toLong(),
                        r.get(EmployessWorked.EMPLOYESS_WORKED.WORKED_HOURS),
                        r.get(EmployessWorked.EMPLOYESS_WORKED.WORKED_DATE)
                    )
                )
            }
            return query
        }
    }

    override fun saveHoursWorked(worker: HoraTrabajo): Long {
        ds.connection.use { connection ->
            val ctx = DSL.using(connection, SQLDialect.POSTGRES)
            val insert = ctx.insertInto(
                EmployessWorked.EMPLOYESS_WORKED,
                EmployessWorked.EMPLOYESS_WORKED.EMPLOYEE_ID,
                EmployessWorked.EMPLOYESS_WORKED.WORKED_HOURS,
                EmployessWorked.EMPLOYESS_WORKED.WORKED_DATE
            ).values(worker.employee_id.toInt(), worker.worked_hours.toInt(), worker.worked_date)
                .returningResult(EmployessWorked.EMPLOYESS_WORKED.ID)
                .fetchOne()

            return insert?.value1()?.toLong() ?: -1L
        }
    }

    override fun updateHoursWorked(workerId: Long, hours: Int): Long {
        ds.connection.use { connection ->
            val ctx = DSL.using(connection, SQLDialect.POSTGRES)
            val update = ctx.update(EmployessWorked.EMPLOYESS_WORKED)
                .set(EmployessWorked.EMPLOYESS_WORKED.WORKED_HOURS, hours)
                .where(EmployessWorked.EMPLOYESS_WORKED.ID.eq(workerId.toInt()))
                .execute()
            return update.toLong()
        }
    }
}