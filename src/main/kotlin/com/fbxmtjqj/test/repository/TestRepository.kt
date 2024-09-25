package com.fbxmtjqj.test.repository

import com.fbxmtjqj.test.entity.Test
import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.springframework.data.jpa.repository.JpaRepository

interface TestRepository : JpaRepository<Test, String>, KotlinJdslJpqlExecutor {
}