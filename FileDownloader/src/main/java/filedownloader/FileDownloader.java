package filedownloader;

import java.nio.file.Path;
import java.util.Optional;

public class FileDownloader {
    private static final String HELP_MSG = "FileDownloader commands \n" +
            "/help to read this prompt" +
            "/dest <path_to_folder> to set a destination" +
            "/exit to exit the program" +
            "/load <URL1> <URL2> etc. to download files";
    private static boolean isRunning = true;
    // в дальнейшем копирую значение destination, так что проблем с тем что оно 1 быть не должно
    private static Path destination = null;
    public static void main(String[] args) {
        InputInterpreter ii = new InputInterpreter();
        while (isRunning) {
            try {
                Command command = ii.getInput();
                executeCommand(command);
            }catch (CommandException ex) {
                System.out.println("Internal error, please contact us and report the issue.");
            }
        }
    }

    private static void executeCommand(Command command) {
        switch (command.type) {
            case HELP -> printHelp();
            case EXIT -> isRunning = false;
            case DEST -> setDestination(command);
            case LOAD -> startLoading(command);
        }
        if (command.isError)
            System.out.println(command.getMSG());
    }

    private static Optional<Path> getDestination() {
        return Optional.ofNullable(destination);
    }

    private static void startLoading(Command command) {
        getDestination().ifPresentOrElse(path ->
                        command.getURLs().ifPresent(urls -> new Loader(urls, path)),
                () -> System.out.println("Use /dest to set destination first"));
    }

    private static void setDestination(Command command) {
        command.getDirectory().ifPresent(newPath -> destination = newPath);
    }

    private static void printHelp() {
        System.out.println(HELP_MSG);
    }
}
