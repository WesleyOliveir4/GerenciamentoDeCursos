package br.pucpr.authserver.controller

import br.pucpr.authserver.service.AlunoService
import br.pucpr.authserver.dto.CursoRequest
import br.pucpr.authserver.service.CursoService
import br.pucpr.authserver.model.Curso
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cursos")
class CursoController(
    private val cursoService: CursoService,
    private val alunoService: AlunoService
) {

    @PostMapping("/adicionar")
    fun adicionarCurso(@RequestBody request: CursoRequest): ResponseEntity<Curso> {
        val curso = cursoService.adicionarCurso(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(curso)
    }

    @GetMapping
    fun listarCursos(): ResponseEntity<List<Curso>> {
        val cursos = cursoService.listarCursos()
        return ResponseEntity.ok(cursos)
    }

    @DeleteMapping("/{cursoId}/alunos/{alunoId}")
    fun removerAlunoDoCurso(@PathVariable cursoId: Long, @PathVariable alunoId: Long) {
        alunoService.removerAlunoDoCurso(alunoId, cursoId)
    }

    @DeleteMapping("/{cursoId}")
    fun removerCurso(@PathVariable cursoId: Long) {
        cursoService.removerCurso(cursoId)
    }
}