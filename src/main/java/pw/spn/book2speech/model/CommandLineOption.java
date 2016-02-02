package pw.spn.book2speech.model;

public enum CommandLineOption {
    ACCESS_KEY("a"), SECRET_KEY("s"), INPUT_FILE("i"), OUTPUT_DIR("o"), ENDPOINT("e"), CODEC("c"), RATE("r"), VOLUME(
            "v"), NAME("n"), LANGUAGE("l"), GENDER("g"), VOICE_LIST("vl"), HELP("h"), ENCODING("en");

    private final String shortcut;

    private CommandLineOption(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getShortcut() {
        return shortcut;
    }

}
