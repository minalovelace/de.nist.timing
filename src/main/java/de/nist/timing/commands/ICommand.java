package de.nist.timing.commands;

import java.util.Map;

import de.nist.timing.events.Metadata;

/*
 * Base interface for every command.
 */
public interface ICommand {

	public Map<String, String> getArguments();

	public Metadata getMetadata();
}