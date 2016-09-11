package rentbazaar.wyby;

import java.util.ArrayList;

/**
 * (TODO): This should be reading from the backend (eg: SQL).  For now we have hardcoded the data.
 * Created by krisgane on 9/11/16.
 */
public class MiniListingDataProvider {

    public ArrayList<MiniListingData> getData() {
        ArrayList<MiniListingData> miniListings = new ArrayList<>();
        miniListings.add(
                new MiniListingData(R.drawable.dress_expensive, "Calvin Klein Gown", 10.0, 0.3));
        miniListings.add(
                new MiniListingData(R.drawable.rolex, "Rolex Watch", 25.0, 0.5));
        miniListings.add(
                new MiniListingData(R.drawable.potter_book, "Harry Potter Latest", 1.0, 0.4));
        miniListings.add(
                new MiniListingData(R.drawable.printer_3d, "Awesome 3D Printer", 1.0, 0.4));
        miniListings.add(
                new MiniListingData(R.drawable.car_seat, "Baby Car Seat", 2.5, 0.2));
        miniListings.add(
                new MiniListingData(R.drawable.suedette_shoe, "Suedette Shoe", 1.0, 0.6));
        return miniListings;
    }
}
