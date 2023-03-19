package br.com.treinaweb.twprojetos.api.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
	@ApiImplicitParam(name = "pagina", dataType = "int", paramType = "query", defaultValue = "1", value = "Numero da pagina que deseja recuperar (1..N)"),
	@ApiImplicitParam(name = "tamanho", dataType = "int", paramType = "query", defaultValue = "2", value = "Numero de elementos por pagina."),
	@ApiImplicitParam(name = "ordenacao", dataType = "string", allowMultiple = true, paramType = "query", value = "Criterio de ordenação no formato: propriedade(,asc|desc).")
})
public @interface ApiPageable {
	
	

}
