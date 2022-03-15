package commands;

import java.time.LocalDateTime;

import data.Address;
import data.Coordinates;
import data.Organization;
import data.OrganizationType;
import exceptions.CollectionIsEmptyException;
import exceptions.IncorrectInputInScriptException;
import exceptions.OrganizationNotFoundException;
import exceptions.WrongAmountOfElementsException;
import utility.Asker;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'update'. Updates the information about selected marine.
 */
public class UpdateCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private Asker asker;

    public UpdateCommand(CollectionManager collectionManager, Asker asker) {
        super("update <ID> {element}", "update the value of the collection element whose id is equal to the given one");
        this.collectionManager = collectionManager;
        this.asker = asker;
    }

    /**
     * Executes the command.
     * 
     * @return Command exit status.
     */
    /**
     * The command updates organization with the given ID 
     * 
     * @param argument the argument that the user entered.
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
            Organization oldOrganization = collectionManager.getById(id);
            if (oldOrganization == null)
                throw new OrganizationNotFoundException();

            String name = oldOrganization.getName();
            Coordinates coordinates = oldOrganization.getCoordinates();
            LocalDateTime creationDate = oldOrganization.getCreationDate();
            long annualTurnover = oldOrganization.getAnnualTurnover();
            OrganizationType type = oldOrganization.getType();
            Address address = oldOrganization.getOfficialAddress();

            collectionManager.removeFromCollection(oldOrganization);

            if (asker.askQuestion("Do you want to change the organization's name?"))
                name = asker.askName();
            if (asker.askQuestion("Do youw want to change the organization's coordinates?"))
                coordinates = asker.askCoordinates();
            if (asker.askQuestion("Do you want to change the organization's annual turnover' "))
                annualTurnover = asker.askAnnualTurnover();
            if (asker.askQuestion("Do you want to change the organization's type'"))
                type = asker.askOrganizationType();
            if (asker.askQuestion("Do you want to change the organization's offical address'"))
                address = asker.askAddress();

            collectionManager.addToCollection(new Organization(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    annualTurnover,
                    type,
                    address));
            Console.println("Organization successfully changed!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Collection is empty!");
        } catch (NumberFormatException exception) {
            Console.printerror("ID must be represented by a number!");
        } catch (OrganizationNotFoundException exception) {
            Console.printerror("There is no organization with this ID in the collection!");
        } catch (IncorrectInputInScriptException exception) {
        }
        return false;
    }
}
