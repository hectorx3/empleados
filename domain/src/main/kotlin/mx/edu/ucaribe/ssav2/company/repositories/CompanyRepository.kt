package mx.edu.ucaribe.ssav2.company.repositories

import mx.edu.ucaribe.ssav2.company.models.*
import java.time.LocalDate

interface CompanyRepository {
    fun findAllGenders(): List<Genero>
    fun findAllJobs(): List<Trabajo>
    fun findAllEmployeesBy(
        idJob: Long? = null,
        name: String? = null,
        last_name: String? = null
    ): List<EmpleadoInformacion>
    fun saveEmployee(emp: Empleado): Long
    fun findAllWorkedHoursBy(idEmployee: Long? = null,
                             workerDate: LocalDate? = null,
                             startDate: LocalDate? = null,
                             endDate: LocalDate? = null ): List<EmpHorTrabajo>

    fun saveHoursWorked(worker: HoraTrabajo): Long
    fun updateHoursWorked(workerId: Long, hours: Int): Long
}