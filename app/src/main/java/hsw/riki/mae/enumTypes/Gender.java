package hsw.riki.mae.enumTypes;

public enum Gender {
  MALE("male"),
  FEMALE("female"),
  DIVERSE("diverse");

  private final String genderText;

  Gender(String name) {
    this.genderText = name;
  }

  public String getName() {
    return this.genderText;
  }
}
