package ECDH;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import ECDH.Curve;
import ECDH.DHECLibrary;
import ECDH.Point;
import java.util.Random;
import org.junit.Test;

public class ECDHTest {
  @Test
  public void findModularInverseTest() {
    DHECLibrary ecdh = new DHECLibrary();
    assertEquals(ecdh.superMod(13, -5), -2);
    assertEquals(ecdh.superMod(0, -5), 0);
    assertEquals(ecdh.superMod(12, 2), 0);
    assertEquals(ecdh.superMod(400, -300), -200);
    assertEquals(ecdh.superMod(-1000, -5), 0);
    assertEquals(ecdh.superMod(-17, 9), 1);
  }

  @Test
  public void pointAddTest() {
    DHECLibrary library = new DHECLibrary();
    Curve curve = new Curve(2, 2, 17);
    Point beginPoint = new Point(5, 1);
    Point temp = new Point(5, 1);

    Point res = library.getPontN(1, beginPoint, temp, curve);
    assertTrue(new Point(6, 3).eq(res));

    res = library.getPontN(2, beginPoint, temp, curve);
    assertTrue(new Point(10, 6).eq(res));

    res = library.getPontN(3, beginPoint, temp, curve);
    assertTrue(new Point(3, 1).eq(res));

    res = library.getPontN(4, beginPoint, temp, curve);
    assertTrue(new Point(9, 16).eq(res));

    res = library.getPontN(5, beginPoint, temp, curve);
    assertTrue(new Point(16, 13).eq(res));

    res = library.getPontN(6, beginPoint, temp, curve);
    assertTrue(new Point(0, 6).eq(res));

    res = library.getPontN(7, beginPoint, temp, curve);
    assertTrue(new Point(13, 7).eq(res));

    res = library.getPontN(8, beginPoint, temp, curve);
    assertTrue(new Point(7, 6).eq(res));

    res = library.getPontN(9, beginPoint, temp, curve);
    assertTrue(new Point(7, 11).eq(res));

    res = library.getPontN(10, beginPoint, temp, curve);
    assertTrue(new Point(13, 10).eq(res));

    res = library.getPontN(11, beginPoint, temp, curve);
    assertTrue(new Point(0, 11).eq(res));

    res = library.getPontN(12, beginPoint, temp, curve);
    assertTrue(new Point(16, 4).eq(res));

    res = library.getPontN(13, beginPoint, temp, curve);
    assertTrue(new Point(9, 1).eq(res));

    res = library.getPontN(14, beginPoint, temp, curve);
    assertTrue(new Point(3, 16).eq(res));

    res = library.getPontN(15, beginPoint, temp, curve);
    assertTrue(new Point(10, 11).eq(res));

    res = library.getPontN(16, beginPoint, temp, curve);
    assertTrue(new Point(6, 14).eq(res));

    res = library.getPontN(17, beginPoint, temp, curve);
    assertTrue(new Point(5, 16).eq(res));

    res = library.getPontN(18, beginPoint, temp, curve);
    assertTrue(res == null);

    res = library.getPontN(19, beginPoint, temp, curve);
    assertTrue(res.eq(beginPoint));
  }
}
