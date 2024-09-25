package com.fbxmtjqj.test.config

import com.fbxmtjqj.test.config.DataSourceConstants.SERVICE_DATASOURCE
import com.fbxmtjqj.test.config.DataSourceConstants.SERVICE_ENTITY_MANAGER
import com.fbxmtjqj.test.config.DataSourceConstants.SERVICE_TRANSACTION_MANAGER
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource

@Configuration(proxyBeanMethods = false)
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.fbxmtjqj.test.repository"],
    entityManagerFactoryRef = SERVICE_ENTITY_MANAGER,
    transactionManagerRef = SERVICE_TRANSACTION_MANAGER
)
class JpaConfig(
    private val jpaProperties: JpaProperties,
) {

    @Primary
    @Bean(SERVICE_ENTITY_MANAGER)
    fun serviceEntityManager(
        @Qualifier(SERVICE_DATASOURCE) serviceDataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        val mergedJpaProperties = Properties().apply {
            this.putAll(jpaProperties.properties)
        }

        return LocalContainerEntityManagerFactoryBean().apply {
            this.jpaVendorAdapter = HibernateJpaVendorAdapter()
            this.setJpaProperties(mergedJpaProperties)
            this.dataSource = serviceDataSource
            this.setPackagesToScan("com.fbxmtjqj.test.entity")
        }
    }
}