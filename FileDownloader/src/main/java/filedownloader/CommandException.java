package filedownloader;

// мб очень плохая идея
class CommandException extends Exception {
    private static final String MSG = "Those constructor parameters are not supported," +
            " please read documentation for more details.";
    public CommandException() {
        super(MSG);
    }

}
