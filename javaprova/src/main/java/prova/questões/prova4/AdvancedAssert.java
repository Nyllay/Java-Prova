package prova.questões.prova4;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class AdvancedAssert {

    // 1. Comparação de doubles com tolerância (delta)
    public static void assertDoubleEquals(double expected, double actual, double delta, String message) {
        if (Math.abs(expected - actual) > delta) {
            throw new AssertionError(message + 
                " | Esperado: " + expected + 
                " | Obtido: " + actual + 
                " | Delta permitido: " + delta);
        }
    }

    // 2. Comparação de coleções (ignora ordem)
    public static void assertCollectionEquals(Object[] expected, Object[] actual, String message) {
        List<Object> expectedList = Arrays.asList(expected);
        List<Object> actualList = Arrays.asList(actual);

        if (!(expectedList.containsAll(actualList) && actualList.containsAll(expectedList))) {
            throw new AssertionError(message +
                " | Esperado: " + expectedList +
                " | Obtido: " + actualList);
        }
    }

    // 3. Comparação de objetos (field-by-field usando reflection)
    public static void assertObjectEquals(Object expected, Object actual, String message) {
        if (expected == null || actual == null) {
            if (expected != actual) {
                throw new AssertionError(message +
                    " | Esperado: " + expected +
                    " | Obtido: " + actual);
            }
            return;
        }

        if (!expected.getClass().equals(actual.getClass())) {
            throw new AssertionError(message +
                " | Classes diferentes: " + 
                expected.getClass().getName() + " vs " + actual.getClass().getName());
        }

        try {
            for (Field field : expected.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object expectedValue = field.get(expected);
                Object actualValue = field.get(actual);

                if (expectedValue == null && actualValue == null) continue;
                if (expectedValue == null || !expectedValue.equals(actualValue)) {
                    throw new AssertionError(message +
                        " | Campo: " + field.getName() +
                        " | Esperado: " + expectedValue +
                        " | Obtido: " + actualValue);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Erro ao acessar campos via reflection", e);
        }
    }

    // 4. Verificação de timeout
    public static void assertTimeout(long timeoutMs, Runnable runnable, String message) {
        long start = System.currentTimeMillis();
        runnable.run();
        long duration = System.currentTimeMillis() - start;

        if (duration > timeoutMs) {
            throw new AssertionError(message +
                " | Tempo máximo: " + timeoutMs + "ms" +
                " | Tempo real: " + duration + "ms");
        }
    }
}
