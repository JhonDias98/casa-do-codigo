package br.com.zup.autores

import br.com.zup.client.EnderecoClient
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
class CadastraAutorController(val autorRepository: AutorRepository, val enderecoClient: EnderecoClient) {

    @Post
    @Transactional
    fun cadastra(@Body @Valid request: NovoAutorRequest) : HttpResponse<Any> {

        val enderecoResponse = enderecoClient.consultarEndereco(request.cep)

        val autor = request.paraAutor(enderecoResponse.body()!!)
        autorRepository.save(autor)

        //Definindo header
        val uri = UriBuilder.of("/autores/{id}")
                            .expand(mutableMapOf(Pair("id", autor.id)))

        return HttpResponse.created(uri)

    }
}