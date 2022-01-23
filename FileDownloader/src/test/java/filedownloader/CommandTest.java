package filedownloader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.net.URL;
import java.nio.file.Path;

public class CommandTest {
    @Test
    void constructorWithOneParamShouldReturnCorrectCommand() throws CommandException {
        Command com = new Command(Command.ComType.HELP);
        Assertions.assertSame(com.type, Command.ComType.HELP);
    }
    @Test
    void constructorWithPathShouldThrowCommandExceptionOnWrongComType(@TempDir Path tempDir)
            throws CommandException {
        Assertions.assertThrows(CommandException.class, () -> new Command(Command.ComType.LOAD, tempDir ));
    }
    @Test
    void constructorWithURLsShouldThrowCommandExceptionWithNoUrls() {
        URL[] urls = new URL[0];
        Assertions.assertThrows(CommandException.class, () -> new Command(Command.ComType.LOAD, urls));
    }
}
