package br.com.zup.utils.validation

import javax.validation.Constraint
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*

@MustBeDocumented //Documentada pela javadoc
@Target(FIELD, CONSTRUCTOR) //Onde podera ser usada
@Retention(RUNTIME) //Para o Java reter em tempo de execução
@Constraint(validatedBy = [PlacaValidator::class]) //Classe que irá de fato validar
annotation class Placa(val message: String = "placa com formato invalido")