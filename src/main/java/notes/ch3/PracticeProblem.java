package notes.ch3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Description of the purpose of this file.
 * @date 11/08/24
 */

// 1. Write a program to monitor files from folder '.src/main/input'
// 2. Logic should monitor any change in the folder.
// 3. Once the files are present then it should start processing files in separate thread.
// 4. Validation is to check each line for empty spaces and throw error in case line is empty.

public class PracticeProblem {

    public static String INPUT_PATH = "src/main/files/input";

    public static void main(String[] args) throws InterruptedException {
        FileWatcher watcher = new FileWatcher(INPUT_PATH);
        Thread thread = new Thread(watcher);
        thread.start();
        Thread.sleep(65000);
        watcher.shutdown();
    }
}

class FileOperationExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Exception occurred during file processing" + e.getMessage());
    }
}

class FileWatcher implements Runnable {

    private volatile boolean isActive = true;

    String path;

    public FileWatcher(String path) {
        this.path = path;
    }

    public void shutdown() {
        System.out.println("Shutting down watcher");
        this.isActive = false;
    }

    @Override
    public void run() {
        File inputDirectory = new File(path);

        while (isActive) {
            if (inputDirectory.listFiles().length != 0) {
                Arrays.stream(inputDirectory.listFiles())
                        .forEach(
                                file -> {
                                    System.out.println("Processing file with name : " + file.getName());
                                    Thread t = new Thread(new FileProcessor(file));
                                    t.setUncaughtExceptionHandler(new FileOperationExceptionHandler());
                                    t.start();
                                }
                        );
                sleep();
            }
        }
    }

    private void sleep() {
        try {
            System.out.println("Watcher going to sleep for 3 sec.... ");
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

class FileProcessor implements Runnable {

    File file;
    public static String OUTPUT_PATH = "src/main/files/output/";

    public FileProcessor(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_PATH + file.getName()));

            Files.lines(Path.of(file.getCanonicalPath()))
                    .map(line -> {
                        try {
                            return cleanAndEncrypt(line, file.getName());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })   // removing empty spaces
                    .map(line -> line + "\n")  // adding /n
                    .forEach(line -> {
                        try {
                            writer.write(line);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String cleanAndEncrypt(String line, String fileName) throws Exception {
        if (line.equals(""))
            throw new Exception("Incorrect line encountered , cancelling processing of file - " + fileName);
        return line.trim();
    }
}
