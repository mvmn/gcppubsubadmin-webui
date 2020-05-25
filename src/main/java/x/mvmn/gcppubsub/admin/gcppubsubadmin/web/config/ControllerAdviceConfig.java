package x.mvmn.gcppubsub.admin.gcppubsubadmin.web.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto.GenericErrorDto;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.exception.NotFoundException;

@RestControllerAdvice
public class ControllerAdviceConfig {

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public GenericErrorDto onNotFound(NotFoundException nfe) {
		return GenericErrorDto.of(nfe.getMessage());
	}
}
