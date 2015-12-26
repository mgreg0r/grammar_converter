/* Alphabet symbol representation
 * author : Marcin Gregorczyk
 */

package gramatyka;

public abstract class Symbol implements Comparable<Symbol> {

  public abstract boolean isTerminal();

  public int compareTo(Symbol symbol) {
    if(isTerminal())
      if(symbol.isTerminal())
        return 0;
      else return 1;
    if(symbol.isTerminal())
      return -1;

    //non-terminal symbols are compared in alphabetic order
    return Character.compare(representation, symbol.representation);
  }

  public String toString() {
    return Character.toString(representation);
  }

  protected char representation;

}
