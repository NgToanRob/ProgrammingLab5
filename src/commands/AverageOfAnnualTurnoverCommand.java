package commands;

import exceptions.CollectionIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'sum_of_health'. Prints the sum of health of all marines.
 */
public class AverageOfAnnualTurnoverCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public AverageOfAnnualTurnoverCommand(CollectionManager collectionManager) {
        super("average_of_annual_turnover", "read and execute the script from the specified file");
        this.collectionManager = collectionManager;
    }

    /**
     * Prints the average value of annual turnover for all organizations
     * 
     * @param argument The argument is the input that the user gives to the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongAmountOfElementsException();
            double averageOfAnnualTurnover = collectionManager.getAverageOfAnnualTurnover();

            Console.println("Average value of annual turnover for all organizations: " + averageOfAnnualTurnover);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("The collection is empty!");
        }
        return false;
    }
}
