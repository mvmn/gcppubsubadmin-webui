package x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericErrorDto {
	public String error;

	public static GenericErrorDto of(String error) {
		return new GenericErrorDto(error);
	}
}
