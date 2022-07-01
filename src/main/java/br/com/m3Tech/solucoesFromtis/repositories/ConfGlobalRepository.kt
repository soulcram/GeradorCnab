package br.com.m3Tech.solucoesFromtis.repositories

import br.com.m3Tech.solucoesFromtis.model.ConfGlobal
import org.springframework.data.jpa.repository.JpaRepository

interface ConfGlobalRepository : JpaRepository<ConfGlobal, Integer> {
}