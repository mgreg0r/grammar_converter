/* Greibach grammar implementation
 * author : Marcin Gregorczyk
 */

package gramatyka;

public class NormalGreibachGrammar extends ContextFreeGrammar {

  public NormalGreibachGrammar(ContextFreeGrammar grammar) {
    super(grammar);
    if(!super.ifGreibach())
      throw new GrammarException("Failed attempt to construct Greibach grammar");

  }

  protected NormalGreibachGrammar(SymbolContainer newSymbols,
      ProductionContainer productionContainer, NonterminalSymbol startSymbol) {

    super(newSymbols, productionContainer, startSymbol);
  }

  @Override
  public String getGrammarForm() {
    return "Greibach";
  }
}
