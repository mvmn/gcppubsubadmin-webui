package x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {

	@NotBlank
	private String name;
}
