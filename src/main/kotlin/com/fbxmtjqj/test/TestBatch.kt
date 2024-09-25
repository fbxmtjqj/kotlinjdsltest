package com.fbxmtjqj.test

import com.fbxmtjqj.test.entity.Test
import com.fbxmtjqj.test.repository.TestRepository
import com.navercorp.spring.batch.plus.job.ClearRunIdIncrementer
import com.navercorp.spring.batch.plus.kotlin.configuration.BatchDsl
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class TestBatch(
    private val batch: BatchDsl,
    private val transactionManager: PlatformTransactionManager,
    private val testRepository: TestRepository,
) {
    @Bean
    fun testJob() =
        batch {
            job("testBatch") {
                incrementer(ClearRunIdIncrementer.create())
                step("testBatch") {
                    tasklet(testTasklet(), transactionManager)
                }
            }
        }

    @Bean
    @StepScope
    fun testTasklet() = Tasklet { _, _ ->
        // jpa
        testRepository.deleteById("testEmail")

        // kotlin-jdsl
        testRepository.delete {
            deleteFrom(
                entity(Test::class)
            ).where(
                path(Test::email).eq("testEmail")
            )
        }

        RepeatStatus.FINISHED
    }
}