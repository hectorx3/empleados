package mx.edu.ucaribe.ssav2.company.usecases

import mx.edu.ucaribe.ssav2.company.models.Empleado
import mx.edu.ucaribe.ssav2.company.models.EmployeeDTO
import mx.edu.ucaribe.ssav2.company.repositories.CompanyRepository
import java.time.LocalDate
import java.time.Period

class RegisterEmployee
constructor(
    private val companyRepository: CompanyRepository
) : UseCase<Empleado, EmployeeDTO> {
    override fun handle(command: Empleado): EmployeeDTO {
        var years = Period.between(command.birthdate, LocalDate.now()).years
        if(years > 17) {
            var findEmp = companyRepository.findAllEmployeesBy(name = command.name, last_name = command.last_name) //empRepository.findByNameAndLast_name(command.name, command.last_name)
            if(findEmp.isEmpty()) {
                val emp = companyRepository.saveEmployee(command)
                return EmployeeDTO(emp.toInt(), true)
            }
        }
        return EmployeeDTO(-1, false)
    }
}