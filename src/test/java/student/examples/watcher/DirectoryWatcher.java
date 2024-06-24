package student.examples.watcher;

import java.io.IOException;
import java.nio.file.*;

public class DirectoryWatcher {
    private Path directoryPath;
    private WatchService watchService;

    public DirectoryWatcher(Path directoryPath) throws IOException, InterruptedException {
        this.directoryPath = directoryPath;
        watchService = FileSystems.getDefault().newWatchService();
//        Path directoryPath = Paths.get(directory);
        directoryPath.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        while (true){
            System.out.println("Before take");
            WatchKey key = watchService.take();
            System.out.println("+++++++========");
            for (WatchEvent<?> event : key.pollEvents()){
                if (event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE) || event.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY)){
                    System.out.println("Created!!!" + event.context());
                    ProcessBuilder processBuilder = new ProcessBuilder("mvn.cmd", "test");
                    processBuilder.redirectErrorStream();
                    processBuilder.inheritIO();
                    Process process = processBuilder.start();
                    int exitCode = process.waitFor();
                    System.out.println("Test finish code:  " + exitCode);
                }else if (event.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY)){
                    System.out.println("Modified!!!");
                }
            }
            boolean valid  = key.reset();
            if (!valid){
                System.out.println("Whatckey is no longer valid");
                break;
            }
        }
    }
}
