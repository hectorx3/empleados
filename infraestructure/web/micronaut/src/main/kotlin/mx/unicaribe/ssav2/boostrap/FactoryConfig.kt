package mx.unicaribe.ssav2.boostrap

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton
import mx.edu.ucaribe.ssav2.company.QueryCompany
import mx.edu.ucaribe.ssav2.company.repositories.CompanyRepository
import mx.edu.ucaribe.ssav2.company.usecases.RegisterEmployee
import mx.edu.ucaribe.ssav2.company.usecases.RegisterWorkedHours
import mx.unicaribe.ssav2.company.CompanyRepositoryImp
import javax.validation.constraints.NotNull

@ConfigurationProperties(value = "customdatabase")
class CustomSourcesConfiguration {

    @get:NotNull
    var host: String? = null

    @get:NotNull
    var user: String? = null

    @get:NotNull
    var password: String? = null
}

@Factory
class RepositoriesFactory {

    @Bean
    fun getCompanyRepository(conf: CustomSourcesConfiguration): CompanyRepository {
        val config = HikariConfig()
        config.jdbcUrl = conf.host
        config.username = conf.user
        config.password = conf.password
        config.maximumPoolSize = 15
        val a = CompanyRepositoryImp(HikariDataSource(config))
        return a
    }
}

@Factory
class UseCasesFactory {

    @Singleton
    fun queryCompany(companyRepository: CompanyRepository): QueryCompany {
        return QueryCompany(companyRepository)
    }

    @Singleton
    fun registerEmployee(companyRepository: CompanyRepository): RegisterEmployee {
        return RegisterEmployee(companyRepository)
    }

    @Singleton
    fun registerEmployeeHoursWorked(companyRepository: CompanyRepository): RegisterWorkedHours {
        return RegisterWorkedHours(companyRepository)
    }
}