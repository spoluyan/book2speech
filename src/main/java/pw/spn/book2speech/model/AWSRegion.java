package pw.spn.book2speech.model;

public enum AWSRegion {
    EU("eu", "tts.eu-west-1.ivonacloud.com"), USE("use", "tts.us-east-1.ivonacloud.com"), USW("usw",
            "tts.us-west-2.ivonacloud.com");

    private String key;
    private String endpoint;

    private AWSRegion(String key, String endpoint) {
        this.key = key;
        this.endpoint = endpoint;
    }

    public static AWSRegion fromKey(String key) {
        for (AWSRegion region : values()) {
            if (region.key.equals(key)) {
                return region;
            }
        }
        throw new IllegalArgumentException("Unable to find AWS region with key " + key);
    }

    public String getEndpoint() {
        return endpoint;
    }
}
