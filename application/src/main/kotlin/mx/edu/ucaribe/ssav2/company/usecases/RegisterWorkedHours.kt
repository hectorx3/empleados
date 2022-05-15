package mx.edu.ucaribe.ssav2.company.usecases

import mx.edu.ucaribe.ssav2.company.models.EmployeeDTO
import mx.edu.ucaribe.ssav2.company.models.HoraTrabajo
import mx.edu.ucaribe.ssav2.company.repositories.CompanyRepository
import java.time.LocalDate

class RegisterWorkedHours
constructor(
    private val companyRepository: CompanyRepository
) : UseCase<HoraTrabajo, EmployeeDTO>{
    override fun handle(command: HoraTrabajo): EmployeeDTO {
        var isAfterDate = LocalDate.now().isAfter(command.worked_date)
        if(command.worked_hours <= 0 || command.worked_hours > 20  || command.employee_id == null || !  isAfterDate) return EmployeeDTO(-1, false)

        var findEmp = companyRepository.findAllWorkedHoursBy(idEmployee = command.employee_id, workerDate = command.worked_date).firstOrNull()

        if(findEmp == null) {
            var nuevoEmp = companyRepository.saveHoursWorked(
                HoraTrabajo(employee_id = command.employee_id, worked_hours = command.worked_hours, worked_date = command.worked_date))

            return EmployeeDTO(nuevoEmp.toInt(), true)
        }

        val totalHours = findEmp.horasTrabajo.worked_hours + command.worked_hours
        if(totalHours <= 20) {
            companyRepository.updateHoursWorked(command.employee_id, totalHours)
            return EmployeeDTO(command.employee_id.toInt(), true)
        }

        return EmployeeDTO(-1, false)
    }
}