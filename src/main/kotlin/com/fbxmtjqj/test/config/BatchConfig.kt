package com.fbxmtjqj.test.config

import com.fbxmtjqj.test.config.DataSourceConstants.BATCH_DATASOURCE
import com.fbxmtjqj.test.config.DataSourceConstants.BATCH_TRANSACTION_MANAGER
import com.fbxmtjqj.test.config.DataSourceConstants.SERVICE_ENTITY_MANAGER
import com.fbxmtjqj.test.config.DataSourceConstants.SERVICE_TRANSACTION_MANAGER
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.support.JdbcTransactionManager
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration(proxyBeanMethods = false)
class BatchConfig {
    @Primary
    @Bean(BATCH_TRANSACTION_MANAGER)
    fun batchTransactionManager(
        @Qualifier(BATCH_DATASOURCE) batchDataSource: DataSource
    ): PlatformTransactionManager {
        return JdbcTransactionManager(batchDataSource)
    }

    @Bean(SERVICE_TRANSACTION_MANAGER)
    fun serviceTransactionManager(
        @Qualifier(SERVICE_ENTITY_MANAGER) serviceEntityManager: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
        return JpaTransactionManager().apply {
            this.entityManagerFactory = serviceEntityManager.`object`
        }
    }
}
