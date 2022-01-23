package filedownloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class InputInterpreter {

    private static final Scanner scanner = new Scanner(System.in);
    private Command read(String userString) throws CommandException {
        String[] parsedStr = userString.split(" ");
        switch (parsedStr[0]) {
            case "/exit":
                return new Command(Command.ComType.EXIT);
            case "/help":
                return new Command(Command.ComType.HELP);
            case "/dest":
                return validatePath(parsedStr[1]);
            case "/load":
                return validateURLs(parsedStr);
            default:
                return new Command(Command.ComType.ERROR_UNKNOWN_COM);
        }
    }
    private Command validatePath(String pathStr) throws CommandException {
        Path path = Paths.get(pathStr);
        if (Files.exists(path)) {
            return new Command(Command.ComType.DEST, path);
        } else {
            return new Command(Command.ComType.ERROR_INCOR_DIR);
        }
    }
    private Command validateURLs(String[] suspectedURLs) throws CommandException {
        int urlCount = suspectedURLs.length - 1;
        URL[] urls = new URL[urlCount];
        try {
            for (int i = 1; i < urlCount + 1; i++) {
                URL url = new URL(suspectedURLs[i]);
            }
        } catch (MalformedURLException urlException) {
            return new Command(Command.ComType.ERROR_INCOR_URL);
        }
        return new Command(Command.ComType.LOAD, urls);
    }
    public Command getInput() throws CommandException {
        String inputString = scanner.nextLine();
        return read(inputString);
    }
}
