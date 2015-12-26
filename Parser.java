/* Parser creating grammar from strings
 * author : Marcin Gregorczyk
 */

package gramatyka;

import java.util.HashSet;

public class Parser {

  public SymbolContainer getSymbols() {
    return symbols;
  }

  public ProductionContainer getProductions() {
    return productions;
  }

  public NonterminalSymbol getStartSymbol() {
    return startSymbol;
  }

  public Parser(String newTerminals, String newNonterminals, String[][] newProductions) {
    if(newTerminals.length() == 0)
      throw new GrammarException("Terminals list is empty");
    if(newNonterminals.length() == 0)
      throw new GrammarException("Nonterminals list is empty");
    if(newProductions.length != newNonterminals.length())
      throw new GrammarException("Amount of productions and nonterminals don't match");

    symbols = new SymbolContainer(newTerminals, newNonterminals);

    for(int i = 0; i < newProductions.length; i++) {
      NonterminalSymbol left = symbols.getNonterminalByChar(newNonterminals.charAt(i));
      HashSet<String> productionSet = new HashSet<String>();

      for(int j = 0; j < newProductions[i].length; j++) {
        Symbol[] right = new Symbol[newProductions[i][j].length()];
        for(int z = 0; z < right.length; z++) {
          char curr = newProductions[i][j].charAt(z);

          right[z] = symbols.getTerminalByChar(curr);
          if(right[z] == null)
            right[z] = symbols.getNonterminalByChar(curr);
          if(right[z] == null)
            throw new GrammarException("Symbol " + curr + " does not exist");
        }
        if(productionSet.contains(newProductions[i][j]))
          throw new GrammarException("Production " + right + " -> " + newProductions[i][j] + " is not unique");
        productionSet.add(newProductions[i][j]);
        Production production = new Production(left, right);
        productions.add(production);
      }
      productionSet.clear();
    }

    startSymbol = symbols.getNonterminalByChar(newNonterminals.charAt(0));
  }

  private SymbolContainer symbols;
  private ProductionContainer productions = new ProductionContainer();
  private NonterminalSymbol startSymbol;

}
