package pw.spn.book2speech.model;

public enum Rate {
    XSLOW("xs", "x-slow"), SLOW("s", "slow"), MEDIUM("m", "medium"), FAST("f", "fast"), XFAST("xf", "x-fast");

    private final String key;
    private final String rate;

    private Rate(String key, String rate) {
        this.key = key;
        this.rate = rate;
    }

    public static Rate fromKey(String key) {
        for (Rate rate : values()) {
            if (rate.key.equals(key)) {
                return rate;
            }
        }
        throw new IllegalArgumentException("Unable to find rate with key " + key);
    }

    public String getRate() {
        return rate;
    }
}
