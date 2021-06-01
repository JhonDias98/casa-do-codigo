package br.com.zup.client

import br.com.zup.endereco.EnderecoResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client(value = "\${cep.url}")
interface EnderecoClient {
    @Get("{cep}/json")
    fun consultarEndereco(cep: String) : HttpResponse<EnderecoResponse>
}