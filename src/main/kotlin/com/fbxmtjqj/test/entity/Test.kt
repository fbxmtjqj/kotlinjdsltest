package com.fbxmtjqj.test.entity

import jakarta.persistence.*
import org.springframework.data.domain.Persistable
import kotlin.jvm.Transient

@Entity(name = "test")
class Test(
    @Id
    val email: String,
) : Persistable<String> {

    override fun getId() = email

    override fun isNew() = _isNew

    @Transient
    private var _isNew = true

    @PostPersist
    @PostLoad
    protected fun load() {
        _isNew = false
    }
}