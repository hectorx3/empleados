package mx.unicaribe.ssav2

import io.kotest.core.spec.style.StringSpec
import mx.unicaribe.ssav2.company.CompanyRepositoryImp

class ConnectionTest : StringSpec({
    "Pruebas del repositorio Company" {
        val ds = HikariDataSourceImp.INSTANCE.getDataSource()
        val companyRepo = CompanyRepositoryImp(ds)
        val queryGenero = companyRepo.findAllGenders()
        val queryJobs = companyRepo.findAllJobs()
        val queryHours = companyRepo.findAllWorkedHoursBy()
        println(queryGenero)
        println(queryJobs)
        println(queryHours)

        /*val emp = Empleado(gender_id = 1, job_id = 1, name = "hector", last_name = "alvarez", birthdate = LocalDate.parse("2021-06-13"))
        val modEmployee = companyRepo.saveEmployee(emp)
        println(modEmployee)*/
    }
})

