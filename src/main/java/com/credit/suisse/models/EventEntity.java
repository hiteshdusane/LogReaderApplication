package com.credit.suisse.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "events_table")
public class EventEntity {

	@Id
	@Column(name = "event_id")
	private String id;

	@Column(name = "event_type")
	private String type;

	@Column(name = "event_host")
	private String host;

	// private String timestamp;

	@Column(name = "event_duration")
	private Long eventDuration;

	@Column(name = "alert")
	private Boolean alert;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Long getEventDuration() {
		return eventDuration;
	}

	public void setEventDuration(Long eventDuration) {
		this.eventDuration = eventDuration;
	}

	public Boolean getAlert() {
		return alert;
	}

	public void setAlert(Boolean alert) {
		this.alert = alert;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventEntity other = (EventEntity) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "EventEntity [id=" + id + ", type=" + type + ", host=" + host + ", eventDuration=" + eventDuration
				+ ", alert=" + alert + "]";
	}

}
