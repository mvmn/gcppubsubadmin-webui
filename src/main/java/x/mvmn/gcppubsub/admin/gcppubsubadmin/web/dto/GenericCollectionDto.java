package x.mvmn.gcppubsub.admin.gcppubsubadmin.web.dto;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericCollectionDto<T> {

	public Collection<T> values;
}
