package mx.unicaribe.ssav2.boostrap

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton
import mx.edu.ucaribe.ssav2.company.QueryCompany
import mx.edu.ucaribe.ssav2.company.repositories.CompanyRepository
import mx.edu.ucaribe.ssav2.company.usecases.RegisterEmployee
import mx.edu.ucaribe.ssav2.company.usecases.RegisterWorkedHours
import mx.unicaribe.ssav2.company.CompanyRepositoryImp
import javax.validation.constraints.NotBlank


@ConfigurationProperties("database")
class CustomSourcesConfiguration {
    @NotBlank
    var env = ""

    @NotBlank
    var enviroments: List<DatabaseEnviroments> = listOf()

    class DatabaseEnviroments{
        var tag: String = ""
        var host: String = ""
        var user: String = ""
        var password: String = ""
    }
}

@Factory
class RepositoriesFactory {

    @Singleton
    fun getDataSource(conf: CustomSourcesConfiguration): HikariDataSource {
        val config = HikariConfig()
        val defaultDatabaseConf = conf.enviroments.first { d -> d.tag.lowercase() == conf.env.lowercase() }
        config.jdbcUrl = defaultDatabaseConf.host
        config.username = defaultDatabaseConf.user
        config.password = defaultDatabaseConf.password
        config.maximumPoolSize = 15
        return HikariDataSource(config)
    }

    @Singleton
    fun companyRepository(ds: HikariDataSource): CompanyRepository {
        return CompanyRepositoryImp(ds)
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



