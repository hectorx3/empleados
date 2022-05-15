package mx.unicaribe.ssav2

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.r2dbc.pool.ConnectionPool
import io.r2dbc.pool.ConnectionPoolConfiguration
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactoryOptions
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import java.io.File
import java.io.FileInputStream

class ConfigurationFile {
    lateinit var runmode: String
    lateinit var database: List<DataBaseConfigurationFile>
}

class DataBaseConfigurationFile {
    lateinit var tag: String
    lateinit var host: String
    lateinit var user: String
    lateinit var password: String
}

private fun getCurrentDatabaseConfig(runMode: String, databasesConf: List<DataBaseConfigurationFile>): DataBaseConfigurationFile =
     databasesConf.first { d -> d.tag.lowercase() == runMode.lowercase() }

class R2dbcDataSource private constructor() {
    private var ds: ConnectionPool

    init {
        val applicationFile = FileInputStream(File("src/main/resources/application.yml"))
        val configFile = Yaml(Constructor(ConfigurationFile::class.java)).load<ConfigurationFile>(applicationFile)
        val defaultDatabaseConf = getCurrentDatabaseConfig(configFile.runmode, configFile.database)
        val connFactory = ConnectionFactories.get(
            ConnectionFactoryOptions
                .parse(defaultDatabaseConf.host)
                .mutate()
                .option(ConnectionFactoryOptions.USER, defaultDatabaseConf.user)
                .option(ConnectionFactoryOptions.PASSWORD, defaultDatabaseConf.password)
                .build()
        )

        ds = ConnectionPool(ConnectionPoolConfiguration
            .builder(connFactory)
            .maxSize(10)
            .build())

    }

    private object Holder {
        val conn = R2dbcDataSource()
    }

    companion object {
        val instance: R2dbcDataSource by lazy { Holder.conn }
    }

    fun getDataSource(): ConnectionPool {
        return ds
    }

}

class HikariDataSourceImp private constructor() {
    private val config = HikariConfig()
    private var ds: HikariDataSource
    init {
        val i = FileInputStream(File("src/main/resources/application.yml"))
        val configFile = Yaml(Constructor(ConfigurationFile::class.java)).load<ConfigurationFile>(i)
        val defaultDatabaseConf = getCurrentDatabaseConfig(configFile.runmode, configFile.database)
        config.jdbcUrl = defaultDatabaseConf.host
        config.username = defaultDatabaseConf.user
        config.password = defaultDatabaseConf.password
        config.maximumPoolSize = 15
        ds = HikariDataSource(config)
    }

    private object Holder {
        val config = HikariDataSourceImp()
    }

    companion object {
        val INSTANCE: HikariDataSourceImp by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { Holder.config }
    }

    fun getDataSource(): HikariDataSource {
        return ds
    }
}

