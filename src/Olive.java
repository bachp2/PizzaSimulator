import java.awt.*;

/**
 * Created by bachp on 3/8/2017.
 */
public class Olive extends Vegetable {
    Olive(){
        super("The olive, known by the botanical name Olea europaea, meaning \"European olive\", " +
                "is a species of small tree in the family Oleaceae, " +
                "found in the Mediterranean Basin from Portugal to the Levant, the Arabian Peninsula, " +
                "and southern Asia as far east as China, as well as the Canary Islands, Mauritius and Réunion",
                new Money(6, 89),
                45,
                Color.BLACK
        );
    }
    Olive(Color c){
        this();
        this.setColor(c);
    }
}
