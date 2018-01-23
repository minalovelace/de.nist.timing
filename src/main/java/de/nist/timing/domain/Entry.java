package de.nist.timing.domain;

import java.time.LocalDate;
import java.time.LocalTime;

interface Entry {

	LocalDate date();

	LocalTime time();

	EntryKind kind();

}