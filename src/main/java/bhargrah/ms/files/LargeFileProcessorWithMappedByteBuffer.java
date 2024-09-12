package bhargrah.ms.files;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class LargeFileProcessorWithMappedByteBuffer {

    private static final int BUFFER_SIZE = 1024 * 1024; // 1 MB buffer size

    public static void main(String[] args) {
        Path filePath = Paths.get("/Users/rahulbhargava/Desktop/coding_pad/java_multithreading_and_parallel_programming/src/main/files/largefile/gc.log");

        try (FileChannel fileChannel = FileChannel.open(filePath, StandardOpenOption.READ)) {
            long fileSize = fileChannel.size();
            System.out.println(fileSize / (BUFFER_SIZE * 1024) + " GB");
            System.out.println(fileSize / BUFFER_SIZE + " MB");

            long position = 0;

            while (position < fileSize) {
                // Determine the size of the next chunk
                long chunkSize = Math.min(BUFFER_SIZE, fileSize - position);

                // Map the chunk of the file into memory
                MappedByteBuffer mappedBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, position, chunkSize);

                // Process the mapped buffer
                processBuffer(mappedBuffer);

                // Move to the next chunk
                position += chunkSize;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processBuffer(MappedByteBuffer buffer) {
        // Example processing: read the buffer byte by byte
        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            System.out.println((char) b);
            // Perform your processing here (e.g., convert to character, count occurrences, etc.)
        }
    }
}
