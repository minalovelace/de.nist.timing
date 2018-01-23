package de.nist.timing.domain;

import java.util.UUID;

class Entity {
	private final UUID id;

	Entity() {
		id = UUID.randomUUID();
	}

	Entity(final UUID id) {
		this.id = id;
	}

	UUID id() {
		return id;
	}
}
