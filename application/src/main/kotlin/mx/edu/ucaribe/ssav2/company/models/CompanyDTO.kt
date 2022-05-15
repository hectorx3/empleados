package mx.edu.ucaribe.ssav2.company.models

import java.math.BigDecimal

data class EmployeeDTO(
    val id: Int,
    val success: Boolean
)

data class TotalHoursDTO(
    val total_worked_hours: Int,
    val success: Boolean
)

data class PaymentDTO(
    val payment: BigDecimal,
    val success: Boolean
)
