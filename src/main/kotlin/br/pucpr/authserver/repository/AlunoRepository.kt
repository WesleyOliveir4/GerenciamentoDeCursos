package br.pucpr.authserver.repository

import br.pucpr.authserver.model.Aluno
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface AlunoRepository : JpaRepository<Aluno, Long> {
    fun existsByNome(nome: String): Boolean
    fun findByIdAndCursoId(alunoId: Long, cursoId: Long): Aluno?

    @Modifying
    @Transactional
    @Query("UPDATE Aluno a SET a.curso = NULL WHERE a.curso.id = :cursoId")
    fun removerCursoDosAlunos(cursoId: Long)
}