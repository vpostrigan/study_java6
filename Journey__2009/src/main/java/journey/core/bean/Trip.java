package journey.core.bean;

import java.util.Date;

public class Trip extends Bean {

	private static final long serialVersionUID = 852011524230641379L;
	
	protected long owner;
    protected String description;
    protected String shortDescription;
    protected Date start;
    protected Date end;
    protected float score;
    protected String photo;
    protected Date created;
    protected String booking_note;
    protected int is_public;
        
	public Trip(long id, long owner, String description, String shortDescription,
			Date start, Date end, float score, String photo, Date created,
			String booking_note, int is_public) {
		super(id);
		this.owner = owner;
		this.description = description;
		this.shortDescription = shortDescription;
		this.start = start;
		this.end = end;
		this.score = score;
		this.photo = photo;
		this.created = created;
		this.booking_note = booking_note;
		this.is_public = is_public;
	}

	public long getOwner() {
		return owner;
	}

	public void setOwner(long owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getBooking_note() {
		return booking_note;
	}

	public void setBooking_note(String booking_note) {
		this.booking_note = booking_note;
	}

	public int getIs_public() {
		return is_public;
	}

	public void setIs_public(int is_public) {
		this.is_public = is_public;
	}
}
