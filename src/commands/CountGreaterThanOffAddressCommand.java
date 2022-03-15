/**
 * Prints the number of elements whose officialAddress field value is greater than the specified one
 */
package commands;

import exceptions.CollectionIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import data.Address;
import utility.CollectionManager;
import utility.Console;

/**
 * Prints the number of elements whose officialAddress field value is greater
 * than the specified one
 */
public class CountGreaterThanOffAddressCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public CountGreaterThanOffAddressCommand(CollectionManager collectionManager) {
        super("count_greater_than_official_address",
                "print the number of elements whose officialAddress field value is greater than the specified one");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * 
     * @param argument The argument is the street of the official address you want
     *                 to compare.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongAmountOfElementsException();
            String anony = "aaaaa";
            Address toCompare = new Address(argument, anony);
            Console.println(collectionManager.getCountGreaterThanOffAddress(toCompare));
            return true; // create Address from street and zipcode

        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");

        } catch (CollectionIsEmptyException exception) {
            Console.printerror("The collection is empty!");
        }
        return true;
    }

}
