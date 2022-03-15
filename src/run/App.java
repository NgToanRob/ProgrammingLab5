package run;

import java.util.Scanner;

import commands.AddCommand;
import commands.AddIfMinCommand;
import commands.ClearCommand;
import commands.CountGreaterThanOffAddressCommand;
import commands.ExecuteScriptCommand;
import commands.ExitCommand;
import commands.FilterGreaterThanTypeCommand;
import commands.HelpCommand;
import commands.HistoryCommand;
import commands.InfoCommand;
import commands.RemoveByIdCommand;
import commands.RemoveGreaterCommand;
import commands.SaveCommand;
import commands.ShowCommand;
import commands.AverageOfAnnualTurnoverCommand;
import commands.UpdateCommand;
import utility.CollectionManager;
import utility.CommandManager;
import utility.Console;
import utility.FileManager;
import utility.Asker;


/**
 * Main application class. Creates all instances and runs the program.
 * 
 * @author Nguyen Toan
 */
public class App {
    public static void main(String[] args) {
        try (Scanner userScanner = new Scanner(System.in)) {
            final String envVariable = "TOAN";

            Asker asker = new Asker(userScanner);
            FileManager fileManager = new FileManager(envVariable);
            CollectionManager collectionManager = new CollectionManager(fileManager);
            CommandManager commandManager = new CommandManager(
                    new HelpCommand(),
                    new InfoCommand(collectionManager),
                    new ShowCommand(collectionManager),
                    new AddCommand(collectionManager, asker),
                    new UpdateCommand(collectionManager, asker),
                    new RemoveByIdCommand(collectionManager),
                    new ClearCommand(collectionManager),
                    new SaveCommand(collectionManager),
                    new ExitCommand(),
                    new ExecuteScriptCommand(),
                    new AddIfMinCommand(collectionManager, asker),
                    new RemoveGreaterCommand(collectionManager, asker),
                    new HistoryCommand(),
                    new AverageOfAnnualTurnoverCommand(collectionManager),
                    new CountGreaterThanOffAddressCommand(collectionManager),
                    new FilterGreaterThanTypeCommand(collectionManager));
            Console console = new Console(commandManager, userScanner, asker);

            console.interactiveMode();
        }
    }
}
