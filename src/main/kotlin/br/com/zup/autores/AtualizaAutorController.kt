package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import io.micronaut.validation.Validated
import javax.transaction.Transactional

@Controller("/autores/{id}")
class AtualizaAutorController(val autorRepository: AutorRepository) {

    @Put
    @Transactional
    fun atualizar(@PathVariable id: Long, descricao: String) : HttpResponse<Any> {
        val possivelAutor = autorRepository.findById(id)

        if(possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }
        possivelAutor.let { autor ->
            autor.get().descricao = descricao
            /*
            Como está com @Transactional, quando a transação é aberta, no fechamento dela
            todos os dados alterados serão salvos, sendo assim o autorRepository.update(autor.get())
            não é necessário
             */
            autorRepository.update(autor.get())
            return HttpResponse.ok(DetalhesDoAutorResponse(autor.get()))
        }
    }
}