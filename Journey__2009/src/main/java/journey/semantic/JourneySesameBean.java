package journey.semantic;

import java.util.Collection;

import org.openrdf.model.Value;

public class JourneySesameBean {
	
	private Collection<Value> subjects;
	private Collection<Value> predicaties;
	private Collection<Value> objects;
	
	public JourneySesameBean(Collection<Value> subjects,
			Collection<Value> predicaties, Collection<Value> objects) {
		super();
		this.subjects = subjects;
		this.predicaties = predicaties;
		this.objects = objects;
	}

	public Collection<Value> getSubjects() {
		return subjects;
	}

	public void setSubjects(Collection<Value> subjects) {
		this.subjects = subjects;
	}
	
	public Collection<Value> getPredicaties() {
		return predicaties;
	}

	public void setPredicaties(Collection<Value> predicaties) {
		this.predicaties = predicaties;
	}

	public Collection<Value> getObjects() {
		return objects;
	}

	public void setObjects(Collection<Value> objects) {
		this.objects = objects;
	}
}
