package de.nist.timing.commands;

import de.nist.timing.events.Event;
import de.nist.timing.repositories.EventRepository;

/*
 * This class receives commands and creates corresponding events based on the given command.
 * Improvement: Think abaout making the CommandHandler a singleton. Let the CommandHandler
 * create the commands, because two commands at the same time (i.e. where the difference
 * is less than a millisecond) will lead to an error.
 */
public class CommandHandler {

    private final ICommand command;

    public CommandHandler(ICommand command) {
        this.command = command;
    }

    /*
     * Executes the given command. The command creates an event, which will be saved
     * in the event repository.
     */
    public Boolean execute() {
        Event event = this.command.createEvent();
        return new EventRepository().write(event);
    }

    public String getEtag() {
        return this.command.getMetadata().getEtag();
    }
}
