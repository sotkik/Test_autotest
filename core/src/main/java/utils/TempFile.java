package utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TempFile extends File implements AutoCloseable {

    public TempFile(Path path) {
        super(path.toUri());
    }

    @Override
    public void close() {
        FileUtils.deleteQuietly(this);
    }

    public static TempFile getTempFile() throws IOException {
        return new TempFile(Files.createFile(getNextTempFileLocation()));
    }

    private static Path getNextTempFileLocation() {
        return Paths
                .get(createTmpFileLocation())
                .normalize()
                .toAbsolutePath();
    }

    private static String createTmpFileLocation() {
        return String.format("%s/%d%d.file",
                System.getProperty("java.io.tmpdir"),
                System.nanoTime(),
                RandomUtils.nextInt(10)
        );
    }
}