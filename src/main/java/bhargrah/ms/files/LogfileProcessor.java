package bhargrah.ms.files;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Description of the purpose of this file.
 * @date 31/08/24
 */
public class LogfileProcessor {

    static final String logPath = "/Users/rahulbhargava/Desktop/coding_pad/java_multithreading_and_parallel_programming/src/main/files/largefile/gc.log";
    static final int BLOCK_SIZE = 1024; // 1MB

    public static void main(String[] args) throws IOException {

        //fileChannelProcessing();
        lineProcessor();

    }

    private static void fileChannelProcessing() throws IOException {
        Map<Character, Integer> dict = new HashMap<>();

        Path path = Paths.get(logPath);

        FileChannel channel = FileChannel.open(path, StandardOpenOption.READ); // select mode to operate on

        System.out.println("File Size (MB) :" + channel.size());

        long fileSize = channel.size();
        long position = 0;

        while (position < fileSize) {

            long chunkSize = Math.min(BLOCK_SIZE, fileSize - position);

            System.out.println("Chunk Size : " + chunkSize);
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, position, chunkSize);

            while (buffer.hasRemaining()) {
                //System.out.println((char) buffer.get());
                Character character = (char) buffer.get();
                dict.put(character, dict.getOrDefault(character, 0) + 1);
            }

            position = position + BLOCK_SIZE;
        }

        System.out.println(dict.get('A'));
    }

    static final String logF1Path = "/Users/rahulbhargava/Desktop/coding_pad/java_multithreading_and_parallel_programming/src/main/files/input/f1.txt";

    public static void lineProcessor() throws IOException {
        Path path = Paths.get(logF1Path);

        Files.lines(path)
                //.map(line -> line.length())
                //.filter(length -> length > 10)
                .filter(line -> line.contains("a"))
                .forEach(System.out::println);
    }

}
