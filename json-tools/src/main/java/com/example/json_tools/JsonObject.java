package com.example.json_tools;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class JsonObject {

    @GetMapping("/object")
    public String objectPage() {
        return """
                            <html>
                                <body>
                                    <h1>JSON Tools – object</h1>
                                    <p>Use POST /object with JSON body:</p>
                                    <pre>
                {
                  "className": { ... },
                  "json": { ...,
                            ...,
                            ... }
                }
                                    </pre>
                                </body>
                            </html>
                        """;
    }

    @PostMapping("/object")
    public String object(@RequestBody Map<String, Object> body) {

        String className = (String) body.get("className");
        Object json = body.get("json");

        if (className == null || json == null || !(json instanceof Map)) {
            return "Błędne dane wejściowe";
        }

        StringBuilder code = new StringBuilder();
        generateClass(className, (Map<String, Object>) json, code, new HashSet<>());

        return code.toString();
    }

    private void generateClass(
            String className,
            Map<String, Object> json,
            StringBuilder code,
            Set<String> generatedClasses) {

        if (generatedClasses.contains(className)) {
            return;
        }
        generatedClasses.add(className);

        code.append("public class ").append(className).append(" {\n\n");

        for (Map.Entry<String, Object> entry : json.entrySet()) {
            String fieldName = entry.getKey();
            Object value = entry.getValue();

            String fieldType = resolveType(fieldName, value, code, generatedClasses);

            code.append("    private ").append(fieldType)
                    .append(" ").append(fieldName).append(";\n");
        }

        code.append("\n}\n\n");
    }

    private String resolveType(
            String fieldName,
            Object value,
            StringBuilder code,
            Set<String> generatedClasses) {

        if (value == null) {
            return "Object";
        }

        if (value instanceof Integer) return "Integer";
        if (value instanceof Long) return "Long";
        if (value instanceof Double) return "Double";
        if (value instanceof Boolean) return "Boolean";
        if (value instanceof String) return "String";

        if (value instanceof Map) {
            String nestedClass = capitalize(fieldName);
            generateClass(nestedClass, (Map<String, Object>) value, code, generatedClasses);
            return nestedClass;
        }

        if (value instanceof List<?> list) {
            if (list.isEmpty()) {
                return "List<Object>";
            }
            String elementType = resolveType(fieldName + "Item", list.get(0), code, generatedClasses);
            return "List<" + elementType + ">";
        }

        return "Object";
    }

    private String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
