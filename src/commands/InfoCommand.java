package commands;

import java.time.LocalDateTime;

import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'info'. Prints information about the collection.
 */
public class InfoCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info",
                "print information about the collection to standard output (type, initialization date, number of elements, etc.)");
        this.collectionManager = collectionManager;
    }

   /**
    * Prints information about the collection
    * 
    * @param argument The argument is the string that is passed to the command.
    * @return Command exit status.
    */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongAmountOfElementsException();
            LocalDateTime lastInitTime = collectionManager.getLastInitTime();
            String lastInitTimeString = (lastInitTime == null) ? "no initialization has yet taken place in this session"
                    : lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

            LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
            String lastSaveTimeString = (lastSaveTime == null) ? "in this session has not yet occurred"
                    : lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

            Console.println("Collection Information:");
            Console.println(" Type: " + collectionManager.collectionType());
            Console.println(" Number of elements: " + collectionManager.collectionSize());
            Console.println(" Date of the last save: " + lastSaveTimeString);
            Console.println(" Date of last initialization: " + lastInitTimeString);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        }
        return false;
    }
}
