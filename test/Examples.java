    // Port of test/Examples.js. Our effects run on the calling thread, so the
    // timeout sleeps before running the effect (a 0 ms timeout still yields).
    public static Object setTimeout = (java.util.function.Function<Object, Object>) (ms) ->
        (java.util.function.Function<Object, Object>) (eff) ->
        (java.util.function.Supplier<Object>) () -> {
            long millis = ((Number) ms).longValue();
            if (millis > 0) {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException interrupted) {
                    Thread.currentThread().interrupt();
                }
            }
            ((java.util.function.Supplier<Object>) eff).get();
            return null;
        };
