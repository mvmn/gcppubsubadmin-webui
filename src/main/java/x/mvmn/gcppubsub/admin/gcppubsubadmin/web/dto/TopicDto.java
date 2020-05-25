package x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TopicDto {

	@NotBlank
	private String name;
}
