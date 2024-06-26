package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Pattern;

public class ResourceUtils {

    public static final ObjectMapper OBJECT_MAPPER = JsonMapper.builder().build();

    @SneakyThrows
    public static String resource(String path) {
        return IOUtils.resourceToString(path, StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public static InputStream resourceAsStream(String path) {
        return ResourceUtils.class.getResourceAsStream(path);
    }

    @SneakyThrows
    public static File resourceAsFile(String path) {
        var tempFile = TempFile.getTempFile();
        FileUtils.copyInputStreamToFile(resourceAsStream(path), tempFile);
        return tempFile;
    }

    public static String resource(String path, Map<String, Object> params) {
        return replace(resource(path), params);
    }

    public static String replace(String value, Map<String, Object> params) {
        return Pattern.compile("\\$\\{(.*?)}")
                .matcher(value)
                .replaceAll(m -> String.valueOf(params.getOrDefault(m.group(1), m.group(1))));
    }
}