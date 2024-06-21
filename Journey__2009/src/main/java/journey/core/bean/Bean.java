package journey.core.bean;

import java.io.Serializable;

/**
 * The base class for all business beans in the project.
 * 
 * In fact, defines only {@link #id} field and implements {@link Serializable}
 * interface.
 */
public abstract class Bean
        implements Serializable {

	private static final long serialVersionUID = -3348519249456600301L;
		
	/** Bean's identifier */
	protected long id = -1;

	/** Public default constructor */
	public Bean() {
		// Do nothing
	}

	/** Initializes bean's identifier */
	public Bean(long id) {
		this.id = id;
	}

	/** Returns bean's identifier */
	public long getId() {
		return this.id;
	}

	/** Sets bean's identifier */
	public void setId(long id) {
		this.id = id;
	}
} 