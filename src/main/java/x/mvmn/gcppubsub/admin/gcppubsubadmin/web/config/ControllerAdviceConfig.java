package x.mvmn.gcppubsub.admin.gcppubsubadmin.web.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.grpc.StatusRuntimeException;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto.GenericErrorDto;
import x.mvmn.gcppubsub.admin.gcppubsubadmin.web.exception.NotFoundException;

@RestControllerAdvice
public class ControllerAdviceConfig {

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public GenericErrorDto onNotFound(NotFoundException e) {
		return GenericErrorDto.of(e.getMessage());
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(StatusRuntimeException.class)
	public GenericErrorDto onGrpcStatusRuntimeException(StatusRuntimeException e) {
		return GenericErrorDto.of(e.getMessage());
	}
}
