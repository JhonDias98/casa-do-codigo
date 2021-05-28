package br.com.zup.autores

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected //No momento de compilação, o Micronaut vai conseguir acessar o código da classe
data class NovoAutorRequest(
    //Usar @field para que o atributo seja validado
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String
) {
    fun paraAutor(): Autor {
        return Autor(nome, email, descricao)
    }
}
