package utility;

import java.util.NoSuchElementException;
import java.util.Scanner;

import data.*;
import exceptions.IncorrectInputInScriptException;
import exceptions.MustBeNotEmptyException;
import exceptions.NotInDeclaredLimitsException;

/**
 * Asks a user a organization's value.
 */
public class Asker {
    private final int MIN_X = -396;
    private final int MAX_Y = 969;
    private final double MIN_ANNUAL_TURNOVER = 0;

    private Scanner userScanner;
    private boolean fileMode;

    public Asker(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
    }

    /**
     * Sets a scanner to scan user input.
     * 
     * @param userScanner Scanner to set.
     */
    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * Get user Scanner when interacting with Console.
     * @return Scanner, which uses for user input.
     */
    public Scanner getUserScanner() {
        return userScanner;
    }

    /**
     * Set asker mode to 'File Mode'.
     */
    public void setFileMode() {
        fileMode = true;
    }

    /**
     * Sets organization asker mode to 'User Mode'.
     */
    public void setUserMode() {
        fileMode = false;
    }

    /**
     * Asks a user the organization's name.
     * 
     * @return Organization's name.
     * @throws IncorrectInputInScriptException If script is running and something
     *                                         goes wrong.
     */
    public String askName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                Console.println("Enter name:");
                Console.print(Console.PS2);
                name = userScanner.nextLine().trim();
                if (fileMode)
                    Console.println(name);
                if (name.equals(""))
                    throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("The name is not recognized!");
                if (fileMode)
                    throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("The name cannot be empty!");
                if (fileMode)
                    throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return name;
    }

    /**
     * Asks a user the organization's X coordinate.
     * 
     * @return Organization's X coordinate.
     * @throws IncorrectInputInScriptException If script is running and something
     *                                         goes wrong.
     */
    public int askX() throws IncorrectInputInScriptException {
        String strX;
        int x;
        while (true) {
            try {
                Console.println("Enter X coordinate:");
                Console.print(Console.PS2);
                strX = userScanner.nextLine().trim();
                if (fileMode)
                    Console.println(strX);
                x = Integer.parseInt(strX);
                if (x < MIN_X)
                    throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("The X coordinate is not recognized!");
                if (fileMode)
                    throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("The X coordinate must be represented by a number!");
                if (fileMode)
                    throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException e) {
                Console.printerror("The X coordinate cannot exceed" + MIN_X + "!");
                if (fileMode)
                    throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return x;
    }

    /**
     * Asks a user the organization's Y coordinate.
     * 
     * @return Organization's Y coordinate.
     * @throws IncorrectInputInScriptException If script is running and something
     *                                         goes wrong.
     */
    public Float askY() throws IncorrectInputInScriptException {
        String strY;
        float y;
        while (true) {
            try {
                Console.println("Enter Y coordinate: ");
                Console.print(Console.PS2);
                strY = userScanner.nextLine().trim();
                if (fileMode)
                    Console.println(strY);
                y = Float.parseFloat(strY);
                if (y > MAX_Y)
                    throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("The Y coordinate is not recognized!");
                if (fileMode)
                    throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("The Y coordinate cannot exceed " + MAX_Y + "!");
                if (fileMode)
                    throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("The Y coordinate must be represented by a number!");
                if (fileMode)
                    throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return y;
    }

    /**
     * Asks a user the organization's coordinates.
     * 
     * @return Organization's coordinates.
     * @throws IncorrectInputInScriptException If script is running and something
     *                                         goes wrong.
     */
    public Coordinates askCoordinates() throws IncorrectInputInScriptException {
        int x;
        float y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }

    /**
     * Asks a user the organization's annual turnover.
     * 
     * @return organization's annual turnover.
     * @throws IncorrectInputInScriptException If script is running and something
     *                                         goes wrong.
     */
    public long askAnnualTurnover() throws IncorrectInputInScriptException {
        String strAnnualTurnover;
        long annualTurnover;
        while (true) {
            try {
                Console.println("Enter annual turnover: ");
                Console.print(Console.PS2);
                strAnnualTurnover = userScanner.nextLine().trim();
                if (fileMode)
                    Console.println(strAnnualTurnover);
                annualTurnover = Long.parseLong(strAnnualTurnover);
                if (annualTurnover < MIN_ANNUAL_TURNOVER)
                    throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Annual turnover is not recognized!");
                if (fileMode)
                    throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Annual turnover must be greater than zero!");
                if (fileMode)
                    throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Health should be represented by a number!");
                if (fileMode)
                    throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return annualTurnover;
    }

    /**
     * Asks a user the organization's type.
     * @return organization's type.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public OrganizationType askOrganizationType() throws IncorrectInputInScriptException {
        String strType;
        OrganizationType type;
        while (true) {
            try {
                Console.println("Organization type list - " + OrganizationType.nameList());
                Console.println("Enter organization type: ");
                Console.print(Console.PS2);
                strType = userScanner.nextLine().trim();
                if (fileMode) Console.println(strType);
                if (strType == "") {
                    type =null;
                } else type = OrganizationType.valueOf(strType.toUpperCase());
                
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("The organization type is not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printerror("The type is not in the list!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return type;
    }

    /**
     * Asks a user the organization chapter's name.
     * 
     * @return Chapter's name.
     * @throws IncorrectInputInScriptException If script is running and something
     *                                         goes wrong.
     */
    public String askStreet() throws IncorrectInputInScriptException {
        String street;
        while (true) {
            try {
                Console.println("Enter street:");
                Console.print(Console.PS2);
                street = userScanner.nextLine().trim();
                if (fileMode)
                    Console.println(street);
                if (street.equals("")) street =null;
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("The street is not recognized!");
                if (fileMode)
                    throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return street;
    }

    public String askZipCode() throws IncorrectInputInScriptException {
        String street;
        while (true) {
            try {
                Console.println("Enter ZipCode:");
                Console.print(Console.PS2);
                street = userScanner.nextLine().trim();
                if (fileMode)
                    Console.println(street);
                if (street.equals(""))
                    throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("The ZipCode is not recognized!");
                if (fileMode)
                    throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("The ZipCode cannot be empty!");
                if (fileMode)
                    throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return street;
    }

    /**
     * Ask the user for the street and zip code
     * 
     * @return Organization's address.
     */
    public Address askAddress() throws IncorrectInputInScriptException {
        String street;
        String ZipCode;
        street = askStreet();
        ZipCode = askZipCode();
        return new Address(street, ZipCode);
    }

    /**
     * Asks a user a question.
     * 
     * @return Answer (true/false).
     * @param question A question.
     * @throws IncorrectInputInScriptException If script is running and something
     *                                         goes wrong.
     */
    public boolean askQuestion(String question) throws IncorrectInputInScriptException {
        String finalQuestion = question + " (Y/N):";
        String answer;
        while (true) {
            try {
                Console.println(finalQuestion);
                Console.print(Console.PS2);
                answer = userScanner.nextLine().trim().toUpperCase();
                if (fileMode)
                    Console.println(answer);
                if (!answer.equals("Y") && !answer.equals("N"))
                    throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Answer not recognized!");
                if (fileMode)
                    throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("The answer must be represented by 'Y' or 'N'!");
                if (fileMode)
                    throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return (answer.equals("Y")) ? true : false;
    }

    @Override
    public String toString() {
        return "Asker (helper class for queries to the user)";
    }
}
