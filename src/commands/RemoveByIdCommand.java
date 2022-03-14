package commands;

import exceptions.CollectionIsEmptyException;
import exceptions.OrganizationNotFoundException;
import exceptions.WrongAmountOfElementsException;
import data.Organization;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'remove_by_id'. Removes the element by its ID.
 */
public class RemoveByIdCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id <ID>", "remove item from collection by ID");
        this.collectionManager = collectionManager;
    }

    /**
     * Remove an organization from the collection
     * 
     * @param argument The argument that is passed to the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty())
                throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0)
                throw new CollectionIsEmptyException();
            long id = Long.parseLong(argument);
            Organization organizationToRemove = collectionManager.getById(id);
            if (organizationToRemove == null)
                throw new OrganizationNotFoundException();
            collectionManager.removeFromCollection(organizationToRemove);
            Console.println("Organization successfully removed!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("The collection is empty!");
        } catch (NumberFormatException exception) {
            Console.printerror("ID must be represented by a number!");
        } catch (OrganizationNotFoundException exception) {
            Console.printerror("There is no organization with this ID in the collection!");
        }
        return false;
    }
}
