package br.pucpr.authserver.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
data class Curso(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,
    val descricao: String,

    @JsonManagedReference
    @OneToMany(mappedBy = "curso")
    val alunos: List<Aluno> = emptyList()
)
{
    constructor() : this(0, "","", emptyList())
}

@Entity
data class Aluno(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val nome: String,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "curso_id")
    var curso: Curso?
)
{
    constructor() : this(0, "", Curso())
}

