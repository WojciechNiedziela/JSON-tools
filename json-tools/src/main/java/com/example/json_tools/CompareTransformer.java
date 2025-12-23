package com.example.json_tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

public class CompareTransformer implements TextTransformer {

    @Override
    public String transform(String input) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> body = mapper.readValue(input, Map.class);

            Object left = body.get("left");
            Object right = body.get("right");

            boolean leftEmpty = left == null || (left instanceof Map && ((Map<?, ?>) left).isEmpty());
            boolean rightEmpty = right == null || (right instanceof Map && ((Map<?, ?>) right).isEmpty());

            if (leftEmpty && rightEmpty) {
                return mapper.writeValueAsString(Map.of("message", "brak roznic"));
            }
            if (leftEmpty) {
                return mapper.writeValueAsString(Map.of("error", "plik left json jest pusty"));
            }
            if (rightEmpty) {
                return mapper.writeValueAsString(Map.of("error", "plik right json jest pusty"));
            }

            List<Map<String, Object>> diffs = new ArrayList<>();
            compareObjects(left, right, "", diffs);

            if (diffs.isEmpty()) {
                return mapper.writeValueAsString(Map.of("message", "brak roznic"));
            }

            return mapper.writeValueAsString(
                    Map.of("message", "wykryto roznice", "differences", diffs)
            );
        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON");
        }
    }

    private Map<String, Object> diff(String path, Object left, Object right) {
        return Map.of(
                "path", path.startsWith(".") ? path.substring(1) : path,
                "left", left,
                "right", right
        );
    }

    private void compareObjects(Object left, Object right, String path,
                                List<Map<String, Object>> diffs) {

        if (left == null && right == null) return;

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
                compareObjects(l.get(key), r.get(key), path + "." + key, diffs);
            }
            return;
        }

        if (left instanceof List && right instanceof List) {
            List<?> l = (List<?>) left;
            List<?> r = (List<?>) right;

            int max = Math.max(l.size(), r.size());
            for (int i = 0; i < max; i++) {
                compareObjects(
                        i < l.size() ? l.get(i) : null,
                        i < r.size() ? r.get(i) : null,
                        path + "[" + i + "]",
                        diffs
                );
            }
            return;
        }

        if (!left.equals(right)) {
            diffs.add(diff(path, left, right));
        }
    }
}
