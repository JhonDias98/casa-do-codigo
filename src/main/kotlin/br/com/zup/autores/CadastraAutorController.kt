package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated //Garante que os métodos serão validados
@Controller("/autores")
class CadastraAutorController(val autorRepository: AutorRepository) {

    @Post
    @Transactional
    fun cadastra(@Body @Valid request: NovoAutorRequest) : HttpResponse<Any> {

        val autor = request.paraAutor()
        autorRepository.save(autor)

        //Definindo header
        val uri = UriBuilder.of("/autores/{id}")
                            .expand(mutableMapOf(Pair("id", autor.id)))

        return HttpResponse.created(uri)

    }
}