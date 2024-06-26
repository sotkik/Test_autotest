package utils;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;

public class TestContext extends HashMap<String, Object> {
    public static String replaceHoldersFromContext(String value, TestContext context) {
        if (value == null) {
            return null;
        }

        var matcher = Pattern.compile("\\$\\{([^{}]*)}")
                .matcher(value);

        var sb = new StringBuilder();
        while (matcher.find()) {
            String key = matcher.group(1);
            matcher.appendReplacement(sb, String.valueOf(eval(key, context)));
        }

        matcher.appendTail(sb);
        return sb.toString();
    }

    private static Object eval(String expression, TestContext context) {
        var binding = new Binding(context);
        var sh = new GroovyShell(binding);
        var combinedExpression = "import org.hamcrest.Matchers\n" + expression;
        return sh.evaluate(combinedExpression);
    }
}
