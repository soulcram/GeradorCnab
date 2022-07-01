package br.com.m3Tech.solucoesFromtis.repositories

import br.com.m3Tech.solucoesFromtis.model.Base
import org.springframework.data.jpa.repository.JpaRepository

interface BaseRepository : JpaRepository<Base, Integer> {
}