import java.io.IOException;

/**
 * Example of a corrupt appendable that throws an exception.
 */
public class CorruptAppendableMock implements Appendable {
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Issue transmitting to appendable.");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Issue transmitting to appendable.");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Issue transmitting to appendable.");
  }
}
