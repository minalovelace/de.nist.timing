package de.nist.timing.commands;

import de.nist.timing.events.Metadata;

/*
 * Base interface for every command.
 */
public interface ICommand {

	public String[] getArguments();

	public Metadata getMetadata();
}