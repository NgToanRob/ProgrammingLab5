package commands;

import java.time.LocalDateTime;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;
import utility.Asker;
import utility.CollectionManager;
import utility.Console;
import data.Organization;

/**
 * Command 'add_if_min'. Adds a new element to collection if it's less than the
 * minimal one.
 */
public class AddIfMinCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private Asker asker;

    public AddIfMinCommand(CollectionManager collectionManager, Asker asker) {
        super("add_if_min {element}", "update the value of the collection element whose id is equal to the given one");
        this.collectionManager = collectionManager;
        this.asker = asker;
    }

    /**
     * Adds an organization to the collection
     * 
     * @param argument The argument is the string that is passed to the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongAmountOfElementsException();
            Organization organizationToAdd = new Organization(
                    collectionManager.generateNextId(),
                    asker.askName(),
                    asker.askCoordinates(),
                    LocalDateTime.now(),
                    asker.askAnnualTurnover(),
                    asker.askOrganizationType(),
                    asker.askAddress());

            if (collectionManager.collectionSize() == 0
                    || organizationToAdd.compareTo(collectionManager.getFirst()) < 0) {
                collectionManager.addToCollection(organizationToAdd);
                Console.println("Organization added successfully!");
                return true;
            } else
                Console.printerror(
                        "The value of an Organization is greater than the value of the smallest of the soldiers!");
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {
        }
        return false;
    }
}
