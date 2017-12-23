package de.nist.timing.events;

import java.util.Date;
import java.util.UUID;

public class Metadata {

	private Date date;
	private UUID uuid;

	public Metadata() {
		this.date = new Date();
		this.setUuid(UUID.randomUUID());
	}

	public Metadata(Date date, UUID uuid) {
		this.date = date;
		this.setUuid(uuid);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
}
