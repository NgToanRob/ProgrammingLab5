package commands;

import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'save'. Saves the collection to a file.
 */
public class SaveCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        super("save", "save collection to file");
        this.collectionManager = collectionManager;
    }

    /**
     * Save the collection to the file
     * 
     * @param argument The argument passed to the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongAmountOfElementsException();
            collectionManager.saveCollection();
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        }
        return false;
    }
}