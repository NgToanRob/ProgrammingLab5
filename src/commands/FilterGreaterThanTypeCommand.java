package commands;

import data.OrganizationType;
import exceptions.CollectionIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'filter_by_weapon_type'. Filters the collection by weapon type.
 */
public class FilterGreaterThanTypeCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public FilterGreaterThanTypeCommand(CollectionManager collectionManager) {
        super("filter_greater_than_type  <OrganizationType>",
                "display elements whose organization type field value is equal to the given one");
        this.collectionManager = collectionManager;
    }

    /**
     * Prints the information about the organizations of the selected type
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
            OrganizationType type = OrganizationType.valueOf(argument.toUpperCase());
            String filteredInfo = collectionManager.organizationTypeFilteredInfo(type);
            if (!filteredInfo.isEmpty()) {
                Console.println(filteredInfo);
                return true;
            } else
                Console.println("There are no organizations with the selected organization type in the collection!");
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("The collection is empty!");
        } catch (IllegalArgumentException exception) {
            Console.printerror("Organizations are not on the list!");
            Console.println("List of ranged organizations - " + OrganizationType.nameList());
        }
        return false;
    }
}
