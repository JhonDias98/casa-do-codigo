package br.com.zup.autores

import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/autores")
class BuscaDetalheDeAutoresController(val autorRepository: AutorRepository) {

    @Get
    fun listaPorEmail(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {

        if(email.isBlank()) {
            val autores = autorRepository.findAll()
                .map { autor -> DetalhesDoAutorResponse(autor) }
            return HttpResponse.ok(autores)
        }

        val possivelAutor = autorRepository.findByEmail(email)
        if (possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }
        possivelAutor.let { autor ->
            return HttpResponse.ok(DetalhesDoAutorResponse(autor.get()))
        }

    }

    /*
    Método para usar paginação
    Exemplo de busca:
    api/autores/descricoes?descricao=teste&page=0&size=5&sort=id,desc&sort=nome,asc
     */
    @Get("/descricoes")
    fun listaPorDescricao(@QueryValue(defaultValue = "") descricao: String,
                           paginacao: Pageable): HttpResponse<Any> {
        val autoresEncontrados = autorRepository.findByDescricao(descricao, paginacao)
        if (autoresEncontrados.isEmpty()) {
            return HttpResponse.notFound()
        }
        val autores = autoresEncontrados.map { autor -> DetalhesDoAutorResponse(autor) }
        return HttpResponse.ok(autores)

    }
}