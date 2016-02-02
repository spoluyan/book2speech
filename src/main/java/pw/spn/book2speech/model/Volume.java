package pw.spn.book2speech.model;

public enum Volume {
    SILENT("xxs", "silent"), XSOFT("xs", "x-soft"), SOFT("s", "soft"), MEDIUM("m", "medium"), LOUD("l",
            "loud"), XLOUD("xl", "x-loud");

    private String key;
    private String volume;

    private Volume(String key, String volume) {
        this.key = key;
        this.volume = volume;
    }

    public static Volume fromKey(String key) {
        for (Volume volume : values()) {
            if (volume.key.equals(key)) {
                return volume;
            }
        }
        throw new IllegalArgumentException("Unable to find volume with key " + key);
    }

    public String getVolume() {
        return volume;
    }
}
