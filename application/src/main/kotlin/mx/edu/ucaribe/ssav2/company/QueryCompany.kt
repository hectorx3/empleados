package mx.edu.ucaribe.ssav2.company

import mx.edu.ucaribe.ssav2.company.models.*
import mx.edu.ucaribe.ssav2.company.repositories.CompanyRepository
import java.math.BigDecimal
import java.time.LocalDate

class QueryCompany
constructor(
    private val companyRepository: CompanyRepository
){
    fun findAllGenders(): List<Genero> {
        return companyRepository.findAllGenders()
    }

    fun findAllJobs(): List<Trabajo> {
        return companyRepository.findAllJobs()
    }

    fun getTotalHoursWorked(id: Long?,
                                 startDate: LocalDate?,
                                 endDate: LocalDate?
    ): TotalHoursDTO {
        if(id == null || startDate == null || endDate == null) return TotalHoursDTO(-1, false)
        val total = companyRepository.findAllWorkedHoursBy(id, startDate = startDate, endDate = endDate).sumOf { it.horasTrabajo.worked_hours }
        return TotalHoursDTO(total, total > 0)
    }

    fun getPaymentHoursWorked(id: Long?,
                                   startDate: LocalDate?,
                                   endDate: LocalDate?
    ): PaymentDTO {
        if(id == null || startDate == null || endDate == null) return PaymentDTO(BigDecimal.ZERO, false)
        val pago = companyRepository.findAllWorkedHoursBy(id, startDate = startDate, endDate = endDate).map { it.trabajo.salary }.sumOf { it }
        return PaymentDTO(pago, pago.compareTo(BigDecimal.ZERO) == 1)
    }

    fun findAllEmployes(idJob: Long? = null,
                        name: String? = null,
                        last_name: String? = null): List<EmpleadoInformacion> {
        return companyRepository.findAllEmployeesBy(idJob, name, last_name)
    }

    fun findAllWorkers(): List<EmpHorTrabajo> {
        return companyRepository.findAllWorkedHoursBy()
    }
}