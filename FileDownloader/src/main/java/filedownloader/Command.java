package filedownloader;

import java.net.URL;
import java.nio.file.Path;
import java.util.Optional;

public class Command {
    private static final String ERROR_MSG = "Command is not recognised.";
    private static final String UNKNOWN_COM_MSG = "Unknown command.";
    private static final String DIR_ERR_MSG = "Incorrect directory path.";
    private static final String URL_ERR_MSG = "One or more URLs are incorrect.";
    private static final String OK_MSG = "Command processed successfully.";
    public final boolean isError;
    public final ComType type;
    private final URL[] URLS;
    private final String MSG;
    private final Path directory;
    private static final int NUMBER_OF_ERROR_TYPES = 4;
    public enum ComType {
        ERROR, ERROR_UNKNOWN_COM, ERROR_INCOR_DIR, ERROR_INCOR_URL,
        EXIT, HELP, DEST, LOAD;
    }

    public Command(ComType type, URL[] urls) throws CommandException {
        if (type == ComType.DEST)
            type = ComType.ERROR_INCOR_DIR;
        if (type == ComType.LOAD && (urls == null || urls.length == 0))
            throw new CommandException();
        directory = null;
        this.type = type;
        URLS = urls;
        isError = type.ordinal() < NUMBER_OF_ERROR_TYPES;
        switch (type) {
            case ERROR_UNKNOWN_COM -> MSG = UNKNOWN_COM_MSG;
            case ERROR_INCOR_DIR -> MSG = DIR_ERR_MSG;
            case ERROR_INCOR_URL -> MSG = URL_ERR_MSG;
            case EXIT, HELP, LOAD -> MSG = OK_MSG;
            default -> MSG = ERROR_MSG;
        }
    }
    public Command(ComType type) throws CommandException {
        this(type, (URL[]) null);
    }
    public Command(ComType type, Path directory) throws CommandException {
        if (type != ComType.DEST)
            throw new CommandException();
        this.type = type;
        this.directory = directory;
        isError = false;
        MSG = OK_MSG;
        URLS = null;
    }
    public Optional<URL[]> getURLs() {
        return Optional.ofNullable(URLS);
    }
    public Optional<Path> getDirectory() {
        return Optional.ofNullable(directory);
    }
    public String getMSG() {
        return MSG;
    }


}
