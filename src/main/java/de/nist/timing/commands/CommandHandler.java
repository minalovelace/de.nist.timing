package de.nist.timing.commands;

import de.nist.timing.events.Event;
import de.nist.timing.repositories.EventRepository;
import de.nist.timing.repositories.SnapshotRepository;

/*
 * This class receives commands and creates corresponding events based on the given command.
 */
public class CommandHandler {

    private final ICommand command;

    public CommandHandler(ICommand command) {
        this.command = command;
    }

    /*
     * Executes the given command. The command creates an event, which will be saved
     * in the event repository. Depending on the command, a snapshot will be created
     * and saved to the snapshot repository.
     */
    public boolean execute() {
        Event event = this.command.createEvent();
        Boolean isEventWritten = new EventRepository().write(event);
        Boolean isSnapshotWritten = false;
        if (this.command.isSnapshotPossible())
            isSnapshotWritten = new SnapshotRepository().createSnapshot(getEtag());
        else
            isSnapshotWritten = true;

        return isEventWritten && isSnapshotWritten;
    }

    public String getEtag() {
        return this.command.getMetadata().getEtag();
    }
}