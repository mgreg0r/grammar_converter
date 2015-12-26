/* Context free grammar implementation
 * author : Marcin Gregorczyk
 */

package gramatyka;

import java.util.ArrayList;

public class ContextFreeGrammar {

  public ContextFreeGrammar(ContextFreeGrammar grammar) {
    symbols = grammar.getSymbols();
    productions = grammar.getProductions();
    startSymbol = grammar.getStartSymbol();
  }

  public ContextFreeGrammar(String newTerminals, String newNonterminals,
      String[][] newProductions) {

    Parser parser = new Parser(newTerminals, newNonterminals, newProductions);
    symbols = parser.getSymbols();
    productions = parser.getProductions();
    startSymbol = parser.getStartSymbol();
  }

  public ContextFreeGrammar(SymbolContainer newSymbols,
      ProductionContainer newProductions, NonterminalSymbol newStartSymbol) {

    symbols = newSymbols;
    productions = newProductions;
    startSymbol = newStartSymbol;

  }

  public String getGrammarType() {
    return "context free";
  }

  public String getGrammarForm() {
    return form;				
  }

  public String toString() {
    String result = "Grammar: " + getGrammarType() + "/" + getGrammarForm() + "\n"
        + symbols + "\n"
        + "Productions: \n" + productions;

    return result;
  }

  public boolean ifRegular() {
    boolean tnCheck = false;
    boolean ntCheck = false;

    for(ArrayList<Production> productionsList : productions.getProductions().values()) {
      for(Production production : productionsList) {
        Symbol[] product = production.getRhs();
        if(product.length > 2 || (product.length == 1 && !product[0].isTerminal()))
          return false;
        if(product.length == 2) {
          if(product[0].isTerminal() && !product[1].isTerminal())
            tnCheck = true;
          else if(!product[0].isTerminal() && product[1].isTerminal())
            ntCheck = true;
          else return false;
        }
      }
    }
    if(tnCheck && ntCheck)
      return false;
    return true;
  }

  public boolean ifChomsky() {
    for(ArrayList<Production> productionsList : productions.getProductions().values())
      for(Production production : productionsList)
        if(!production.ifChomsky())
          return false;

    form = "Chomsky";
    return true;
  }

  public boolean ifGreibach() {
    for(ArrayList<Production> productionsList : productions.getProductions().values())
      for(Production production : productionsList)
        if(!production.ifGreibach())
          return false;

    form = "Greibach";
    return true;
  }

  public SymbolContainer getSymbols() {
    return new SymbolContainer(symbols);
  }

  public ProductionContainer getProductions() {
    return new ProductionContainer(productions);
  }

  public NonterminalSymbol getStartSymbol() {
    return startSymbol;
  }

  private String form = "";
  private SymbolContainer symbols;
  private ProductionContainer productions;
  private NonterminalSymbol startSymbol;

}
