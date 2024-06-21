package journey.semantic;

import java.util.Collection;

import org.openrdf.model.Value;

public class JourneySesameBeanColumns {
	
	private Collection<Collection<Value>> column;

	public JourneySesameBeanColumns(Collection<Collection<Value>> column) {
		super();
		this.column = column;
	}

	public Collection<Collection<Value>> getColumn() {
		return column;
	}

	public void setColumn(Collection<Collection<Value>> column) {
		this.column = column;
	}
	
}
