package journey.core.bean;

import java.util.Date;

public class Trip2item extends Bean {

	private static final long serialVersionUID = 370065129434069215L;
	
	protected long trip;
	protected long item;
    protected Date date;
    protected Date added;
    protected long type;
    protected String note;
    protected Date created;
    
    
	public Trip2item(long id, long trip, long item, Date date, Date added, long type,
			String note, Date created) {
		super(id);
		this.trip = trip;
		this.item = item;
		this.date = date;
		this.added = added;
		this.type = type;
		this.note = note;
		this.created = created;
	}


	public long getTrip() {
		return trip;
	}

	public void setTrip(long trip) {
		this.trip = trip;
	}

	public long getItem() {
		return item;
	}

	public void setItem(long item) {
		this.item = item;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}    
}
