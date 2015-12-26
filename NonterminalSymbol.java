/* Representation of a non-terminal alphabet symbol
 * author : Marcin Gregorczyk
 */

package gramatyka;

public class NonterminalSymbol extends Symbol {

  public NonterminalSymbol(char c) {
    if(c < 'A' || c > 'Z')
      throw new GrammarException("Incorrect nonterminal symbol: " +c);
    representation = c;
  }

  @Override
  public boolean isTerminal() {
    return false;
  }

}
