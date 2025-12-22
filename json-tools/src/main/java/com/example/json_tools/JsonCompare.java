package com.example.json_tools;

import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * JsonCompare class is responsible for handling routes under /compare
 *
 * @version 1.0
 */
@RestController
public class JsonCompare {

    /**
     * this method is responsible for handling requests to GET /compare
     *
     * @return String html explaining usage of /compare routes
     */
    @GetMapping("/compare")
    public String comparePage() {
        return """
                            <html>
                                <body>
                                    <h1>JSON Tools – compare</h1>
                                    <p>Use POST /compare with JSON body:</p>
                                    <pre>
                {
                  "left": { ... },
                  "right": { ... }
                }
                                    </pre>
                                </body>
                            </html>
                        """;
    }

    /**
     * this method is used to perform diff on objects
     *
     * @param left left diffed object
     * @param right right diffed object
     * @param path path that is currently diffed
     * @return map of diffs
     */
    private Map<String, Object> diff(String path, Object left, Object right) {
        return Map.of(
                "path", path.startsWith(".") ? path.substring(1) : path,
                "left", left,
                "right", right);
    }

    /**
     * compares objects
     *
     * @param left left diffed object
     * @param right right diffed object
     * @param path path that is currently diffed
     * @param diffs list of diffs
     */
    private void compareObjects(Object left, Object right, String path,
            List<Map<String, Object>> diffs) {

        if (left == null && right == null) {
            return;
        }

        if (left == null || right == null) {
            diffs.add(diff(path, left, right));
            return;
        }

        if (left instanceof Map && right instanceof Map) {
            Map<?, ?> l = (Map<?, ?>) left;
            Map<?, ?> r = (Map<?, ?>) right;

            Set<Object> keys = new HashSet<>();
            keys.addAll(l.keySet());
            keys.addAll(r.keySet());

            for (Object key : keys) {
                compareObjects(
                        l.get(key),
                        r.get(key),
                        path + "." + key,
                        diffs);
            }
            return;
        }

        if (left instanceof List && right instanceof List) {
            List<?> l = (List<?>) left;
            List<?> r = (List<?>) right;

            int max = Math.max(l.size(), r.size());
            for (int i = 0; i < max; i++) {
                Object lv = i < l.size() ? l.get(i) : null;
                Object rv = i < r.size() ? r.get(i) : null;

                compareObjects(
                        lv,
                        rv,
                        path + "[" + i + "]",
                        diffs);
            }
            return;
        }

        if (!left.equals(right)) {
            diffs.add(diff(path, left, right));
        }
    }

    /**
     * this method is responsible for handling requests to POST /compare
     *
     * @param body body of POST /compare request
     * @return Object describing diff between left and right
     */
    @PostMapping("/compare")
    public Object compare(@RequestBody Map<String, Object> body) {

        Object left = body.get("left");
        Object right = body.get("right");

        boolean leftEmpty = left == null || (left instanceof Map && ((Map<?, ?>) left).isEmpty());
        boolean rightEmpty = right == null || (right instanceof Map && ((Map<?, ?>) right).isEmpty());

        if (leftEmpty && rightEmpty) {
            return Map.of("message", "brak roznic");
        }
        if (leftEmpty) {
            return Map.of("error", "plik left json jest pusty");
        }
        if (rightEmpty) {
            return Map.of("error", "plik right json jest pusty");
        }

        List<Map<String, Object>> diffs = new ArrayList<>();
        compareObjects(left, right, "", diffs);

        if (diffs.isEmpty()) {
            return Map.of("message", "brak roznic");
        }

        return Map.of(
                "message", "wykryto roznice",
                "differences", diffs);
    }
}