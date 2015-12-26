/* Terminal alphabet symbol representation
 * author : Marcin Gregorczyk
 */

package gramatyka;

public class TerminalSymbol extends Symbol {

  public TerminalSymbol(char c) {
    if(c < 'a' || c > 'z')
      throw new GrammarException("Incorrect terminal symbol: " +c);
    representation = c;
  }

  @Override
  public boolean isTerminal() {
    return true;
  }

}
