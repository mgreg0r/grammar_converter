// Exception thrown after failed grammar construction

package gramatyka;

public class GrammarException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public String message;

  public String toString() {
    return message;
  }

  public GrammarException(String s) {
    message = s;
  }

}
