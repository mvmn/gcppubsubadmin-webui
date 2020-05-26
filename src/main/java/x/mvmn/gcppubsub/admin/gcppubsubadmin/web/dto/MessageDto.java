package x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

	private String payload;
	private Map<String, String> headers;
}
