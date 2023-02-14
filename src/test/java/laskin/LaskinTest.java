package laskin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LaskinTest { // Luokan nimen loppu pitää olla Test

    // Fixture ("vakiokaluste"): kaikki testit käyttävät samaa laskinta,
    // joka nollataan ennen kutakin testiä.
    private Laskin laskin = new Laskin();
    private final double DELTA = 0.001;

    @BeforeEach
    public void clearCalculator() {
        laskin.nollaa();
    }

    // Testimetodin nimi voi olla mitä tahansa, edessä annotaatio @Test
    // @DisplayName-annotaatio tuottaa tuloksiin metodinimen sijasta käyttäjän
    // haluaman merkkijonon.
    @Test
    @DisplayName("lisaa(): laskeeko yhteenlaskun oikein")
    public void testLisaa() {
        laskin.lisaa(1);
        laskin.lisaa(2);
        assertEquals(3, laskin.annaTulos(), "Lukujen summa 1 ja 2 väärin");
    }

    @Test
    @DisplayName("nollaa(): nollaako laskimen pyydettäessä")
    public void testNollaa() {
        assertEquals(0, laskin.annaTulos(), "laskimen nollaus väärin");
    }

    @Test
    @DisplayName("vahenna(): laskeeko vähennyslaskun oikein")
    public void testVahenna() {
        laskin.lisaa(10);
        laskin.vahenna(2);
        assertEquals(8, laskin.annaTulos(), "Lukujen 10 ja 2 erotus väärin");
    }

    @Test
    @DisplayName("jaa(): laskeeko jakolaskun oikein")
    public void testJaa() {
        laskin.lisaa(8);
        laskin.jaa(2);
        assertEquals(4, laskin.annaTulos(), "Lukujen 8 ja 2 jakolasku väärin");
    }

    // Testin oikea tulos on, että nollallajako heittää poikkeuksen,
    // kutsuja käsittelee sen sitten haluamallaan tavalla
    @Test
    @DisplayName("jaa(): käsitteleekö jakajan arvon 0 oikein")
    public void testJaaNollalla() {
        ArithmeticException poikkeus = assertThrows(ArithmeticException.class, () -> laskin.jaa(0),
                "Nolallajako ei tuottanut poikkeusta");
        assertEquals("Nollalla ei voi jakaa", poikkeus.getMessage(), "Väärä virheilmoitus");
    }

    @Test
    @DisplayName("kerro(): laskeeko kertolaskun oikein")
    public void testKerro() {
        laskin.lisaa(8);
        laskin.kerro(2);
        assertEquals(16, laskin.annaTulos(), "Lukujen 8 ja 2 kertolasku väärin");
    }

    @Test
    @DisplayName("kerro(): laskee double:lla oikein")
    public void testKerroDouble() {
        laskin.lisaa(2);
        laskin.kerro(Math.PI);
        assertEquals(6.28318530718, laskin.annaTulos(), DELTA, "Lukujen 2 ja pii kertolasku väärin");
    }

}