package commands;

import java.time.LocalDateTime;

import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;
import data.Organization;
import utility.Asker;

/**
 * Adds a new organization to the collection
 */
public class AddCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private Asker asker;

    public AddCommand(CollectionManager collectionManager, Asker asker) {
        super("add {element}", "add a new element to the collection");
        this.collectionManager = collectionManager;
        this.asker = asker;
    }

    /**
     * Add an organization to the collection
     * 
     * @param argument The argument is the user input.
     * @return statement of the command.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongAmountOfElementsException();
            collectionManager.addToCollection(new Organization(
                    collectionManager.generateNextId(),
                    asker.askName(),
                    asker.askCoordinates(),
                    LocalDateTime.now(),
                    asker.askAnnualTurnover(),
                    asker.askOrganizationType(),
                    asker.askAddress()));
            Console.println("Organization added successfully!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {

        }
        return false;
    }
}
