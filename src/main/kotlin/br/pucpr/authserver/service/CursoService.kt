package br.pucpr.authserver.service

import br.pucpr.authserver.repository.AlunoRepository
import br.pucpr.authserver.repository.CursoRepository
import br.pucpr.authserver.dto.CursoRequest
import br.pucpr.authserver.model.Curso
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class CursoService(
    private val cursoRepository: CursoRepository,
    private val alunoRepository: AlunoRepository
    ) {

    fun adicionarCurso(request: CursoRequest): Curso {
        val curso = Curso(nome = request.nome, descricao = request.descricao)
        return cursoRepository.save(curso)
    }

    fun removerCurso(cursoId: Long) {
        val curso = cursoRepository.findById(cursoId)
        if (curso.isPresent) {
            alunoRepository.removerCursoDosAlunos(cursoId)
            cursoRepository.delete(curso.get())
        } else {
            throw EntityNotFoundException("Curso não encontrado")
        }
    }

    fun listarCursos(): List<Curso> {
        return cursoRepository.findAll()
    }
}