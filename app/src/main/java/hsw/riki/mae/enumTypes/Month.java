package hsw.riki.mae.enumTypes;

public enum Month {
  JANUAR("Januar", "Gennaio", "cold", "snowy", "cozy"),
  FEBRUAR("Februar", "Febbraio", "cold", "stormy", "romantic"),
  MÄRZ("März", "Marzo", "spring-like", "windy", "awakening"),
  APRIL("April", "Aprile", "floral", "rainy", "fresh"),
  MAI("Mai", "Maggio", "sunny", "green", "lively"),
  JUNI("Juni", "Giugno", "warm", "sunny", "active"),
  JULI("Juli", "Luglio", "hot", "summer-like", "joyful"),
  AUGUST("August", "Agosto", "humid", "sunny", "relaxed"),
  SEPTEMBER("September", "Settembre", "autumnal", "windy", "golden"),
  OKTOBER("Oktober", "Ottobre", "cool", "misty", "cozy"),
  NOVEMBER("November", "Novembre", "cold", "rainy", "gloomy"),
  DEZEMBER("Dezember", "Dicembre", "cold", "snowy", "festive");

  private final String name;
  private final String italianName;
  private final String adjective1;
  private final String adjective2;
  private final String adjective3;

  private Month(
      String name, String italianName, String adjective1, String adjective2, String adjective3) {
    this.name = name;
    this.italianName = italianName;
    this.adjective1 = adjective1;
    this.adjective2 = adjective2;
    this.adjective3 = adjective3;
  }

  public String getName() {
    return name;
  }

  public String getItalianName() {
    return italianName;
  }

  public String getAdjective1() {
    return adjective1;
  }

  public String getAdjective2() {
    return adjective2;
  }

  public String getAdjective3() {
    return adjective3;
  }
}
