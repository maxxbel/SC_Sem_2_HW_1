package filedownloader;

import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Loader {
    public enum Status {
        NOT_FOUND,
        LOADED,
        FAILED
    }
    private static ExecutorService executor = Executors.newFixedThreadPool(4);
    public Loader(URL[] urls, Path path) {
        var results = new ArrayList<Future>();
        for (URL url : urls) {
            Future result = executor.submit(MiniLoader::new())
        }
    }
}
