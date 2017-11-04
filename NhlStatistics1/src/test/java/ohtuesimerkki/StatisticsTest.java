package ohtuesimerkki;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by frestmau on 4.11.2017.
 */
public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void statienLuominenToimii(){
        Statistics s = new Statistics(readerStub);
        assertEquals(stats.team("EDM"), s.team("EDM"));
    }
    @Test
    public void pelaajanVoiHakea(){
        Player p = stats.search("Semenko");
        assertEquals(readerStub.getPlayers().get(0), p);
    }

    @Test
    public void parhaatPelaajatLoytyvat(){
        int n = 0;
        List<Player> l = stats.topScorers(2);
        for (Player p : l) {
            assertEquals(stats.topScorers(2).get(n), p);
            n++;
        }
    }

    @Test
    public void pelaajaaEiLoydy(){
        Player p = stats.search("noone");
        assertEquals(null, p);
    }
}
