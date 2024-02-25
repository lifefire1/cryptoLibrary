package ECDH;

public class DHECLibrary {
  public Point getPontN(int n, Point beginPoint, Point temp, Curve curve) {
    for (int i = 0; i < n; i++) {
      if (beginPoint == null) {
        beginPoint = temp;
      } else {
        beginPoint = addTwoPoint(beginPoint, temp, curve);
      }
    }
    return beginPoint;
  }

  public Point addTwoPoint(Point p, Point q, Curve curve) {
    if (p.eq(q)) {
      return nextPoint(p, curve);
    } else {
      return nonEqPoint(p, q, curve);
    }
  }

  public int findX(Point point, int m, int p) {
    return superMod((m * m - 2 * point.x) % p, p);
  }

  public int superMod(int number, int mod) {
    int answer = ((number % mod) + mod) % mod;
    return answer;
  }
  public int findY(Point point, int m, int p) {
    int temp = m * ((point.x) - findX(point, m, p));
    int res = temp - point.y;
    return superMod(res, p);
  }
  public Point nextPoint(Point point, Curve curve) {
    Point res = new Point();
    int m = findM(point.x, point.y, curve);
    res.x = findX(point, m, curve.p);
    res.y = findY(point, m, curve.p);
    return res;
  }

  public Point nonEqPoint(Point p, Point q, Curve curve) {
    int m = findMnonEq(p, q, curve.p);
    if (m == Integer.MIN_VALUE) {
      return null;
    }
    Point res = new Point();
    res.x = findNonEqX(p, q, curve, m);
    res.y = findNonEqY(p, q, curve, m, res);
    return res;
  }

  private int findNonEqY(Point p, Point q, Curve curve, int m, Point res) {
    int y = 0;
    y = p.y + m * (res.x - p.x);
    y = y * -1;
    return superMod(y, curve.p);
  }

  private int findNonEqX(Point p, Point q, Curve curve, int m) {
    int x = 0;
    x = m * m - p.x - q.x;
    return superMod(x, curve.p);
  }

  private int findMnonEq(Point p, Point q, int pr) {
    int up = p.y - q.y;
    int down = p.x - q.x;
    if (up < 0 && down < 0) {
      up = up * -1;
      down = down * -1;
    }
    if (down == 0) {
      return Integer.MIN_VALUE;
    }
    if (up % down != 0) {
      down = findModularInverse(down, pr);
      return superMod(up * down, pr);
    }
    return superMod(up / down, pr);
  }

  public int findM(int xP, int yP, Curve curve) {
    int m = 0;
    int up = (3 * xP * xP + curve.a) % curve.p;
    int down = findModularInverse(((2 * yP) % curve.p), curve.p);
    return ((up * down) % curve.p);
  }

  public int findModularInverse(int b, int mod) {
    int[] result = extendedGCD(b, mod);
    int gcd = result[0];
    int x = result[1];

    if (gcd != 1) {
      return -1; // Обратный элемент не существует
    } else {
      return (x + mod) % mod;
    }
  }

  public int[] extendedGCD(int a, int b) {
    if (a == 0) {
      return new int[] {b, 0, 1};
    } else {
      int[] gcdXY = extendedGCD(b % a, a);
      int gcd = gcdXY[0];
      int y = gcdXY[1];
      int x = gcdXY[2] - (b / a) * y;
      return new int[] {gcd, x, y};
    }
  }
}