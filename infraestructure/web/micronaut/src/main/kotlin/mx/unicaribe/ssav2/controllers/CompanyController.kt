package mx.unicaribe.ssav2.controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject
import mx.edu.ucaribe.ssav2.company.QueryCompany
import mx.edu.ucaribe.ssav2.company.models.*
import mx.edu.ucaribe.ssav2.company.usecases.RegisterEmployee
import mx.edu.ucaribe.ssav2.company.usecases.RegisterWorkedHours
import java.time.LocalDate
import javax.validation.constraints.NotBlank

@Controller("/company")
class CompanyController
@Inject constructor(
    private val queryCompany: QueryCompany,
    private val registerEmployee: RegisterEmployee,
    private val registerWorkedHours: RegisterWorkedHours
){
    @Get("/meet", produces = [MediaType.TEXT_PLAIN])
    fun index() = "Hello World"

    @Get("/genders")
    fun list(): List<Genero> = queryCompany.findAllGenders()

    @Get("/jobs")
    fun jobList(): List<Trabajo> = queryCompany.findAllJobs()

    @Get("/employees{?idjob}")
    fun empJobId(idjob: Long?): List<EmpleadoInformacion> = queryCompany.findAllEmployes(idJob = idjob)

    @Post("/employees")
    fun empSave(
        @Body("gender_id") @NotBlank gender_id: Long,
        @Body("job_id") @NotBlank job_id: Long,
        @Body("name") @NotBlank name: String,
        @Body("last_name") @NotBlank last_name: String,
        @Body("birthdate") @NotBlank birthdate: LocalDate,
    ) : HttpResponse<EmployeeDTO> {
        var emp = registerEmployee.handle(Empleado(gender_id = gender_id, job_id = job_id, name = name, last_name = last_name, birthdate = birthdate))
        return HttpResponse
            .created(emp)
    }

    @Get("/employees/bitacora")
    fun worklist(): List<EmpHorTrabajo> = queryCompany.findAllWorkers()

    @Post("/employees/bitacora")
    fun workSave(
        @Body("employee_id") @NotBlank employee_id: Long,
        @Body("worked_hours") @NotBlank worked_hours: Int,
        @Body("worked_date") @NotBlank worked_date: LocalDate
    ) : HttpResponse<EmployeeDTO> {
        val work = registerWorkedHours.handle(HoraTrabajo(employee_id = employee_id, worked_hours = worked_hours, worked_date = worked_date))
        return HttpResponse
            .created(work)
    }

    @Get("/employees/bitacora/total-hours{?id,start,end}", produces = [MediaType.APPLICATION_JSON])
    fun workerHours(id: Long?,
                         start: String?,
                         end: String?): TotalHoursDTO =
        queryCompany.getTotalHoursWorked(id, LocalDate.parse(start), LocalDate.parse(end))

    @Get("/employees/total-payment{?id,start,end}")
    fun paymentWorkerHours(id: Long?,
                                start: String?,
                                end: String?): PaymentDTO =
        queryCompany.getPaymentHoursWorked(id, LocalDate.parse(start), LocalDate.parse(end))
}