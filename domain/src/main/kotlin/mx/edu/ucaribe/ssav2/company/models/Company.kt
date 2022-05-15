package mx.edu.ucaribe.ssav2.company.models

import java.math.BigDecimal
import java.time.LocalDate

data class Genero (
    var id: Long? = null,
    var name: String
)

data class Trabajo (
    var id: Long? = null,
    var name: String,
    var salary: BigDecimal
)

data class HoraTrabajo(
    var id: Long? = null,
    var employee_id: Long,
    var worked_hours: Int,
    var worked_date: LocalDate
)

data class EmpHorTrabajo(
    var trabajo: Trabajo,
    val horasTrabajo: HoraTrabajo
)

data class Empleado(
    var id: Long? = null,
    var gender_id: Long,
    var job_id: Long,
    var name: String,
    var last_name: String,
    var birthdate: LocalDate
)

data class EmpleadoInformacion(
    var id: Long? = null,
    var gender: Genero,
    var job: Trabajo,
    var name: String,
    var last_name: String,
    var birthdate: LocalDate
)