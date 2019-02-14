package forecast.model;

public enum ConditionType {
    SUNNY("Sunny"), CLOUDY("Cloudy"), RAINY("Rainy"), WINDY("Windy"), SNOWY("Snowy");

    private String type;

    ConditionType(String type) {
        this.type = type;
    }
}
