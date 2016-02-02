package pw.spn.book2speech.model;

public enum SoundCodec {
    MP3("mp3", "MP3"), MP4("mp4", "MP4"), OGG("ogg", "OGG");

    private String key;
    private String codec;

    private SoundCodec(String key, String codec) {
        this.key = key;
        this.codec = codec;
    }

    public static SoundCodec fromKey(String key) {
        for (SoundCodec codec : values()) {
            if (codec.key.equals(key)) {
                return codec;
            }
        }
        throw new IllegalArgumentException("Unable to find sound codec with key " + key);
    }

    public String getCodec() {
        return codec;
    }
}
