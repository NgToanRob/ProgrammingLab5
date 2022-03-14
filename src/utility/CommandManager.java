package utility;

import java.util.ArrayList;
import java.util.List;

import commands.Command;
import exceptions.HistoryIsEmptyException;

/**
 * Operates the commands.
 */
public class CommandManager {
    private final int COMMAND_HISTORY_SIZE = 9;

    private String[] commandHistory = new String[COMMAND_HISTORY_SIZE];
    private List<Command> commands = new ArrayList<>();
    private Command helpCommand;
    private Command infoCommand;
    private Command showCommand;
    private Command addCommand;
    private Command updateCommand;
    private Command removeByIdCommand;
    private Command clearCommand;
    private Command saveCommand;
    private Command exitCommand;
    private Command executeScriptCommand;
    private Command addIfMinCommand;
    private Command removeGreaterCommand;
    private Command historyCommand;
    private Command averageOfAnnualTurnoverCommand;
    private Command countGreaterThanOfficialAddressCommand;
    private Command filterGreaterThanTypeCommand;

    public CommandManager(Command helpCommand, Command infoCommand, Command showCommand, Command addCommand,
            Command updateCommand,
            Command removeByIdCommand, Command clearCommand, Command saveCommand, Command exitCommand,
            Command executeScriptCommand,
            Command addIfMinCommand, Command removeGreaterCommand, Command historyCommand,
            Command averageOfAnnualTurnoverCommand,
            Command countGreaterThanOfficialAddressCommand, Command filterGreaterThanTypeCommand) {
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.showCommand = showCommand;
        this.addCommand = addCommand;
        this.updateCommand = updateCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.clearCommand = clearCommand;
        this.saveCommand = saveCommand;
        this.exitCommand = exitCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.addIfMinCommand = addIfMinCommand;
        this.removeGreaterCommand = removeGreaterCommand;
        this.historyCommand = historyCommand;
        this.averageOfAnnualTurnoverCommand = averageOfAnnualTurnoverCommand;
        this.countGreaterThanOfficialAddressCommand = countGreaterThanOfficialAddressCommand;
        this.filterGreaterThanTypeCommand = filterGreaterThanTypeCommand;

        commands.add(helpCommand);
        commands.add(infoCommand);
        commands.add(showCommand);
        commands.add(addCommand);
        commands.add(updateCommand);
        commands.add(removeByIdCommand);
        commands.add(clearCommand);
        commands.add(saveCommand);
        commands.add(exitCommand);
        commands.add(executeScriptCommand);
        commands.add(addIfMinCommand);
        commands.add(removeGreaterCommand);
        commands.add(historyCommand);
        commands.add(averageOfAnnualTurnoverCommand);
        commands.add(countGreaterThanOfficialAddressCommand);
        commands.add(filterGreaterThanTypeCommand);
    }

    /**
     * Get the command history
     * 
     * @return The command history.
     */
    public String[] getCommandHistory() {
        return commandHistory;
    }

    /**
     * Get list commands in controller.
     * 
     * @return List of manager's commands.
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     * Adds command to command history.
     * 
     * @param commandToStore Command to add.
     */
    public void addToHistory(String commandToStore) {

        for (Command command : commands) {
            if (command.getName().split(" ")[0].equals(commandToStore)) {
                for (int i = COMMAND_HISTORY_SIZE - 1; i > 0; i--) {
                    commandHistory[i] = commandHistory[i - 1];
                }
                commandHistory[0] = commandToStore;
            }
        }
    }

    /**
     * Prints that command is not found.
     * 
     * @param command Comand, which is not found.
     * @return Command exit status.
     */
    public boolean noSuchCommand(String command) {
        Console.println("Command '" + command + "' not found. Type 'help' for help.");
        return false;
    }

    /**
     * Prints info about the all commands.
     * 
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeHelp(String argument) {
        if (helpCommand.execute(argument)) {
            for (Command command : commands) {
                Console.printtable(command.getName(), command.getDescription());
            }
            return true;
        } else
            return false;
    }

    /**
     * Prints the history of used commands.
     * 
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeHistory(String argument) {
        if (historyCommand.execute(argument)) {
            try {
                if (commandHistory.length == 0)
                    throw new HistoryIsEmptyException();

                Console.println("Last used commands:");
                for (int i = 0; i < commandHistory.length; i++) {
                    if (commandHistory[i] != null)
                        Console.println(" " + commandHistory[i]);
                }
                return true;
            } catch (HistoryIsEmptyException exception) {
                Console.println("Not a single command has been used yet!");
            }
        }
        return false;
    }

    /**
     * Executes needed command.
     * 
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeInfo(String argument) {
        return infoCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * 
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeShow(String argument) {
        return showCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * 
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeAdd(String argument) {
        return addCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * 
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeUpdate(String argument) {
        return updateCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * 
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeRemoveById(String argument) {
        return removeByIdCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * 
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeClear(String argument) {
        return clearCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * 
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeSave(String argument) {
        return saveCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * 
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeScript(String argument) {
        return executeScriptCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * 
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeExit(String argument) {
        return exitCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * 
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeAddIfMin(String argument) {
        return addIfMinCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * 
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeRemoveGreater(String argument) {
        return removeGreaterCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * 
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeAverageOfAnnualTurnover(String argument) {
        return averageOfAnnualTurnoverCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * 
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeCountGreaterThanOfficialAddress(String argument) {
        return countGreaterThanOfficialAddressCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * 
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeFilterGreaterThanType(String argument) {
        return filterGreaterThanTypeCommand.execute(argument);
    }

    @Override
    public String toString() {
        return "CommandManager (auxiliary class for working with commands)";
    }
}
