package br.com.zup.autores

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.data.model.Pageable
import java.util.*

@Repository
interface AutorRepository : JpaRepository<Autor, Long>{

    //Derived Query
    fun findByEmail(email: String): Optional<Autor>

    //JPQL
    //Querys com os objetos
    @Query("SELECT a FROM Autor a WHERE a.email = :email")
    fun buscaPorEmail(email: String): Optional<Autor>

    //Native Query
    //Querys usando as nomeclaturas que estão no banco de dados
    @Query(value = "SELECT a FROM autor a WHERE a.nome = :nome",
        nativeQuery = true )
    fun buscaPorNome(nome: String): Optional<Autor>

    //Paginação
    fun findByDescricao(descricao: String, paginacao: Pageable): List<Autor>
}