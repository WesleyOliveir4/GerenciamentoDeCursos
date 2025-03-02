package br.pucpr.authserver.service

import br.pucpr.authserver.repository.AlunoRepository
import br.pucpr.authserver.repository.CursoRepository
import br.pucpr.authserver.model.Aluno
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AlunoService(
    private val alunoRepository: AlunoRepository,
    private val cursoRepository: CursoRepository
) {

    @Transactional
    fun adicionarAlunoNoCurso(alunoNome: String, cursoId: Long): Aluno {
        val curso = cursoRepository.findById(cursoId).orElseThrow {
            RuntimeException("Curso não encontrado")
        }

        if (alunoRepository.existsByNome(alunoNome)) {
            throw RuntimeException("Aluno já cadastrado com esse nome.")
        }

        val aluno = Aluno(nome = alunoNome, curso = curso)

        return alunoRepository.save(aluno)
    }

    fun removerAlunoDoCurso(alunoId: Long, cursoId: Long) {
        val aluno = alunoRepository.findByIdAndCursoId(alunoId, cursoId)
        if (aluno != null) {
            aluno.curso = null
            alunoRepository.save(aluno)
        } else {
            throw EntityNotFoundException("Aluno não encontrado no curso especificado")
        }
    }
}