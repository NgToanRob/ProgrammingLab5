package commands;

import java.time.LocalDateTime;

import exceptions.CollectionIsEmptyException;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;
import utility.Asker;
import data.Organization;

/**
 * Command 'remove_greater'. Removes elements greater than user entered.
 */
public class RemoveGreaterCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private Asker asker;

    public RemoveGreaterCommand(CollectionManager collectionManager, Asker asker) {
        super("remove_greater {element}", "remove from the collection all elements greater than the specified");
        this.collectionManager = collectionManager;
        this.asker = asker;
    }

    /**
     * Remove the organization with the higher annual turnover from the collection
     * 
     * @param argument The argument is the name of the organization to remove.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0)
                throw new CollectionIsEmptyException();
            Organization organizationToCompare = new Organization(
                    collectionManager.generateNextId(),
                    asker.askName(),
                    asker.askCoordinates(),
                    LocalDateTime.now(),
                    asker.askAnnualTurnover(),
                    asker.askOrganizationType(),
                    asker.askAddress());
            collectionManager.removeGreater(organizationToCompare);
            Console.println("Organizaition successfully removed!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("The collection is empty!");
        } catch (IncorrectInputInScriptException exception) {
        }
        return false;
    }
}
