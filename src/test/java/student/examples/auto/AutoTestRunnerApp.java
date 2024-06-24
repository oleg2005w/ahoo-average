package student.examples.auto;

import student.examples.watcher.DirectoryWatcher;

import java.io.IOException;
import java.nio.file.Path;

public class AutoTestRunnerApp {
    public static void main(String[] args) throws IOException, InterruptedException {
//        Path directory = Path.of(AutoTestRunnerApp.class
//                .getClassLoader()
//                .getResource("")
//                .getPath().toString());
        Path pathOf = Path.of("C:\\Users\\olegh\\git\\ahoo-avarage\\src\\test\\resources\\");
        DirectoryWatcher directoryWatcher = new DirectoryWatcher(pathOf);
    }
}
