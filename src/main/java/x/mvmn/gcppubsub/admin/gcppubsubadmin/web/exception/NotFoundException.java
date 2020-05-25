package x.mvmn.gcppubsub.admin.gcppubsubadmin.web.exception;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = -439750762239760504L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message) {
		super(message);
	}
}
