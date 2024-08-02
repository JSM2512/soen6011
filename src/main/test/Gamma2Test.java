import static org.junit.Assert.assertEquals;

import function.Gamma2;
import org.junit.Test;


public class Gamma2Test {

    @Test
    public void testGamma() {
        assertEquals(1.0, Gamma2.gamma(1), 0.0001);
        assertEquals(1.0, Gamma2.gamma(2), 0.0001);
        assertEquals(2.0, Gamma2.gamma(3), 0.0001);
        assertEquals(6.0, Gamma2.gamma(4), 0.0001);
        assertEquals(24.0, Gamma2.gamma(5), 0.0001);
        assertEquals(120.0, Gamma2.gamma(6), 0.0001);
        assertEquals(720.0, Gamma2.gamma(7), 0.0001);
        assertEquals(5040.0, Gamma2.gamma(8), 0.0001);
        assertEquals(40320.0, Gamma2.gamma(9), 0.0001);
        assertEquals(362880.0, Gamma2.gamma(10), 0.0001);
    }

    @Test
    public void testGammaNegative() {
        assertEquals(-3.5448910852514315, Gamma2.gamma(-0.5), 0.001);
        assertEquals(2.36321641291633, Gamma2.gamma(-1.5), 0.001);
        assertEquals(-0.9452570253305973, Gamma2.gamma(-2.5), 0.001);
        assertEquals(0.2700616202436485, Gamma2.gamma(-3.5), 0.001);

    }

    @Test
    public void testGammaFractional() {
        assertEquals(0.886222771337687, Gamma2.gamma(1.5), 0.0001);
        assertEquals(1.3293092327698932, Gamma2.gamma(2.5), 0.0001);
        assertEquals(3.3231692341841845, Gamma2.gamma(3.5), 0.0001);
        assertEquals(11.630583487976116, Gamma2.gamma(4.5), 0.0001);
        assertEquals(52.334681906834675, Gamma2.gamma(5.5), 0.0001);
        assertEquals(287.82096304368906, Gamma2.gamma(6.5), 0.0001);
    }

}
