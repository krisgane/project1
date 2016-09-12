package rentbazaar.wyby;

/**
 * Basic MiniListing information.
 * Created by krisgane on 9/11/16.
 */
public class MiniListingData {
    private final Integer drawableId;
    private final String shortDescription;
    private final Double distance;
    private final Double rent;

    MiniListingData(
            Integer drawableId,
            String shortDescription,
            Double rent,
            Double distance) {
        this.drawableId = drawableId;
        this.shortDescription = shortDescription;
        this.rent = rent;
        this.distance = distance;
    }

    public Double getDistance() {
        return distance;
    }

    public Double getRent() {
        return rent;
    }

    public Integer getDrawableId() {
        return drawableId;
    }

    public String getShortDescription() {
        return shortDescription;
    }
}
