package dev.entze.sge.util;

/**
 * A pair of values. Accessible via getA and getB
 *
 * @param <A> - the first
 * @param <B> - the second
 */
public class Pair<A, B> {

  private final A a;
  private final B b;


  public Pair(A a, B b) {
    this.a = a;
    this.b = b;
  }


  public A getA() {
    return a;
  }

  public B getB() {
    return b;
  }

  @Override
  public int hashCode() {
    int a = this.a.hashCode();
    int b = this.b.hashCode();
    return (((a >>> Integer.SIZE / 2) ^ ((b << Integer.SIZE / 2) >>> Integer.SIZE / 2))
        << Integer.SIZE / 2) | (((a << Integer.SIZE / 2) >>> Integer.SIZE / 2) ^ (b
        >>> Integer.SIZE / 2));
  }
}
