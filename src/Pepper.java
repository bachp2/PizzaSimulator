import java.awt.*;
/**
 * Created by bachp on 3/8/2017.
 */
public class Pepper extends Vegetable {
    Pepper() {
        super("a.k.a peppercorn",
                new Money(5, 12),
                45,
                Color.BLACK
        );
    }
    Pepper(Color c){
        this();
        this.setColor(c);
    }
}
