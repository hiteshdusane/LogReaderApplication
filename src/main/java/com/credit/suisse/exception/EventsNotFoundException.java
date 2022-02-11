package com.credit.suisse.exception;

public class EventsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EventsNotFoundException() {
		super();
	}

	public EventsNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EventsNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EventsNotFoundException(String message) {
		super(message);
	}

	public EventsNotFoundException(Throwable cause) {
		super(cause);
	}

}
