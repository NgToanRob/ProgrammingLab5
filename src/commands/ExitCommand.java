package commands;

import exceptions.WrongAmountOfElementsException;
import utility.Console;

/**
 * Command 'exit'. Checks for wrong arguments then do nothing.
 */
public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "terminate program (without saving to file)");
    }

    /**
     * Executes to exit
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
