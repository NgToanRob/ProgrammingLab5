package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import exceptions.ScriptRecursionException;

/**
 * Operates command input.
 */
public class Console {
    private CommandManager commandManager;
    private Scanner userScanner;
    private Asker asker;
    private List<String> scriptStack = new ArrayList<>();

    protected static final String PS1 = "$ ";
    protected static final String PS2 = "> ";

    public Console(CommandManager commandManager, Scanner userScanner, Asker asker) {
        this.commandManager = commandManager;
        this.userScanner = userScanner;
        this.asker = asker;
    }

    /**
     * Mode for catching commands from user input.
     */
    public void interactiveMode() {
        /**
         * A variable for storing the exit code of the command.
         * 0 : nothing
         * 1 : continue
         * 2 : exit
         */
        int commandStatus;

        String[] userCommand = { "", "" };
        try {
            // A loop for catching commands from user input.
            do {
                Console.print(PS1);
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandManager.addToHistory(userCommand[0]);
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != 2);

        } catch (NoSuchElementException exception) {
            Console.printerror("No user input detected!");
        } catch (IllegalStateException exception) {
            Console.printerror("Unexpected error!");
        }
    }

    /**
     * Mode for catching commands from a script.
     * 
     * @param argument Its argument.
     * @return Exit code.
     */
    public int scriptMode(String argument) {
        String[] userCommand = { "", "" };
        int commandStatus;
        scriptStack.add(argument);
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext())
                throw new NoSuchElementException();
            Scanner tmpScanner = asker.getUserScanner();
            asker.setUserScanner(scriptScanner);
            asker.setFileMode();
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
              // A loop for catching commands from a script: Handle if the line is null.
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }

                Console.println(PS1 + String.join(" ", userCommand));

               // A check for recursion in the script.
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script))
                            throw new ScriptRecursionException();
                    }
                }
                commandStatus = launchCommand(userCommand);
            } while (commandStatus == 0 && scriptScanner.hasNextLine());

            asker.setUserScanner(tmpScanner);
            asker.setUserMode();
            if (commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                Console.println("Check the script for the correctness of the entered data!");
            return commandStatus;
        } catch (FileNotFoundException exception) {
            Console.printerror("The script file was not found!");
        } catch (NoSuchElementException exception) {
            Console.printerror("Let the script file!");
        } catch (ScriptRecursionException exception) {
            Console.printerror("Scripts cannot be called recursively!");
        } catch (IllegalStateException exception) {
            Console.printerror("Unexpected error!");
            System.exit(0);
        } finally {
            scriptStack.remove(scriptStack.size() - 1);
        }
        return 1;
    }

    /**
     * Launch the command.
     * 
     * @param userCommand Command to launch.
     * @return Exit code.
     */
    private int launchCommand(String[] userCommand) {
        switch (userCommand[0]) {
            case "":
                break;
            case "help":
                if (!commandManager.executeHelp(userCommand[1]))
                    return 1;
                break;
            case "info":
                if (!commandManager.executeInfo(userCommand[1]))
                    return 1;
                break;
            case "show":
                if (!commandManager.executeShow(userCommand[1]))
                    return 1;
                break;
            case "add":
                if (!commandManager.executeAdd(userCommand[1]))
                    return 1;
                break;
            case "update":
                if (!commandManager.executeUpdate(userCommand[1]))
                    return 1;
                break;
            case "remove_by_id":
                if (!commandManager.executeRemoveById(userCommand[1]))
                    return 1;
                break;
            case "clear":
                if (!commandManager.executeClear(userCommand[1]))
                    return 1;
                break;
            case "save":
                if (!commandManager.executeSave(userCommand[1]))
                    return 1;
                break;
            case "execute_script":
                if (!commandManager.executeScript(userCommand[1]))
                    return 1;
                else
                    return scriptMode(userCommand[1]);
            case "add_if_min":
                if (!commandManager.executeAddIfMin(userCommand[1]))
                    return 1;
                break;
            case "remove_greater":
                if (!commandManager.executeRemoveGreater(userCommand[1]))
                    return 1;
                break;
            case "history":
                if (!commandManager.executeHistory(userCommand[1]))
                    return 1;
                break;
            case "average_of_annual_turnover":
                if (!commandManager.executeAverageOfAnnualTurnover(userCommand[1]))
                    return 1;
                break;
            case "count_greater_than_official_address":
                if (!commandManager.executeCountGreaterThanOfficialAddress(userCommand[1]))
                    return 1;
                break;
            case "filter_greater_than_type":
                if (!commandManager.executeFilterGreaterThanType(userCommand[1]))
                    return 1;
                break;
                
            case "exit":
                if (!commandManager.executeExit(userCommand[1]))
                    return 1;
                else
                    return 2;
            default:
                if (!commandManager.noSuchCommand(userCommand[0]))
                    return 1;
        }
        return 0;
    }

    /**
     * Prints toOut.toString() to Console
     * 
     * @param toOut Object to print
     */
    public static void print(Object toOut) {
        System.out.print(toOut);
    }

    /**
     * Prints toOut.toString() + \n to Console
     * 
     * @param toOut Object to print
     */
    public static void println(Object toOut) {
        System.out.println(toOut);
    }

    /**
     * Prints error: toOut.toString() to Console
     * 
     * @param toOut Error to print
     */
    public static void printerror(Object toOut) {
        System.out.println("error: " + toOut);
    }

    /**
     * Prints formatted 2-element table to Console
     * 
     * @param element1 Left element of the row.
     * @param element2 Right element of the row.
     */
    public static void printtable(Object element1, Object element2) {
        System.out.printf("%-37s%-1s%n", element1, element2);
    }

    @Override
    public String toString() {
        return "Console (class for handling command input)";
    }
}
