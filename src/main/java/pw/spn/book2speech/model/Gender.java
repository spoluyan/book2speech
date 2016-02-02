package pw.spn.book2speech.model;

public enum Gender {
    FEMALE("f", "Female"), MALE("m", "Male");

    private String key;
    private String gender;

    private Gender(String key, String gender) {
        this.key = key;
        this.gender = gender;
    }

    public static Gender fromKey(String key) {
        for (Gender gender : values()) {
            if (gender.key.equals(key)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Unable to find gender with key " + key);
    }

    public String getGender() {
        return gender;
    }
}
