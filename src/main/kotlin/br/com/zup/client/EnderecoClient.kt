package br.com.zup.client

import br.com.zup.endereco.EnderecoResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client(value = "\${cep.url}")
interface EnderecoClient {
    @Get("{cep}/json")
    fun consultarEndereco(cep: String) : HttpResponse<EnderecoResponse>

    /*
    GET:
    @Get(consumes = [MediaType.APPLICATION_XML])
    @Consumes(MediaType.APPLICATION_XML)
    POST:
    @Post(produces = [MediaType.APPLICATION_XML])
    @Produces(MediaType.APPLICATION_XML)
     */

    @Get("{cep}/xml",consumes = [MediaType.APPLICATION_XML])
    fun consultarEnderecoXml(cep: String) : HttpResponse<EnderecoResponse>

}