package br.pucpr.authserver.controller

import br.pucpr.authserver.service.AlunoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/alunos")
class AlunoController(private val alunoService: AlunoService) {

    @PostMapping("/adicionar")
    fun adicionarAlunoNoCurso(@RequestParam alunoNome: String, @RequestParam cursoId: Long) {
        try {
            val aluno = alunoService.adicionarAlunoNoCurso(alunoNome, cursoId)
            println("Aluno ${aluno.nome} adicionado ao curso com sucesso.")
        } catch (e: Exception) {
            println("Erro: ${e.message}")
        }
    }
}