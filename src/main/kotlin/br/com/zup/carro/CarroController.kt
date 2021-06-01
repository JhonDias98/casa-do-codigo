package br.com.zup.carro

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/carros")
class CarroController {
    @Post
    fun criar(@Body @Valid carro: Carro): io.micronaut.http.HttpResponse<Any> {
        return HttpResponse.ok(carro)
    }
}