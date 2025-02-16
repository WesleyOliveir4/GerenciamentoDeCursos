package br.pucpr.authserver.repository

import br.pucpr.authserver.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository : JpaRepository<Curso, Long>
