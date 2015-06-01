package models;

/**
 * Created by akopylov on 29.05.2015.
 */
public enum ProductTypeEnum {
    STONE("pierre")
    , SCISSORS("ciseaux")
    , PAPER("papier")
    , LIZARD("l?zard")
    , SPOCK("Spock")
    ;

    ProductTypeEnum(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}
