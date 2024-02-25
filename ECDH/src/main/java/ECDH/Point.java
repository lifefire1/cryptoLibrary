package ECDH;

class Point {
  int x;
  int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Point() {}

  @Override
  public String toString() {
    return x + " " + y;
  }

  public boolean eq(Point point) {
    return (this.x == point.x) && (this.y == point.y);
  }
}