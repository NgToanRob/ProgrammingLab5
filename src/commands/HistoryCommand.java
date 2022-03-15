package commands;

import exceptions.WrongAmountOfElementsException;
import utility.Console;

/**
 * Command 'history'. It's here just for logical structure.
 */
public class HistoryCommand extends AbstractCommand {

    public HistoryCommand() {
        super("history", "display history of used commands");
    }

    /**
     * The command shows 9 last commands.
     * 
     * @param argument The argument passed to the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongAmountOfElementsException();
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        }
        return false;
    }
}
