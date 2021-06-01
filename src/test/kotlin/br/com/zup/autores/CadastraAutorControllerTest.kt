package br.com.zup.autores

import br.com.zup.client.EnderecoClient
import br.com.zup.endereco.EnderecoResponse
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import org.mockito.Mockito

import org.junit.jupiter.api.Assertions.*
import javax.inject.Inject

@MicronautTest
internal class CadastraAutorControllerTest{ //Internal: acessivel somente dentro do módulo

    @field:Inject
    lateinit var enderecoClient: EnderecoClient

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient //Lateinit: "atrasa a inicialização"

    @Test
    fun `deve cadastrar um novo autor`() {
        // cenario
        val novoAutorRequest = NovoAutorRequest("Jonathan", "jonathan@gmail.com", "teste",
                                                    "09321-255", "21")
        val enderecoResponse = EnderecoResponse("Rua Ari", "São Paulo", "SP")

        Mockito.`when`(enderecoClient.consultarEndereco(novoAutorRequest.cep))
                        .thenReturn(HttpResponse.ok(enderecoResponse))

        val request = HttpRequest.POST("/autores", novoAutorRequest)

        // acao
        val response = client.toBlocking().exchange(request, Any::class.java)

        // corretude
        assertEquals(HttpStatus.CREATED, response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location")!!.matches("/autores/\\d".toRegex()))
    }

    @MockBean(EnderecoClient::class)
    fun enderecoMock():EnderecoClient {
        return Mockito.mock(EnderecoClient::class.java)
    }
}