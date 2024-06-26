package utils;

import java.util.Map;
import java.util.Map.Entry;


import static java.util.stream.Collectors.toMap;
import static utils.TestContext.replaceHoldersFromContext;

public class RestUtils {

    public Map<String, String> extentParams(Map<String, String> queryParams, TestContext context) {
        return queryParams.entrySet()
                .stream()
                .collect(toMap(Entry::getKey, entry -> replaceHoldersFromContext(entry.getValue(), context)));
    }

}
