/* Chomsky grammar implementation
 * author : Marcin Gregorczyk
 */
package gramatyka;


import java.util.ArrayList;
import java.util.Collections;

public class NormalChomskyGrammar extends ContextFreeGrammar {

  public NormalChomskyGrammar(ContextFreeGrammar grammar) {			
    super(grammar);
    if(!super.ifChomsky())
      throw new GrammarException("Failed attempt to construct Chomsky grammar");

  }

  @Override
  public String getGrammarForm() {
    return "Chomsky";
  }

  public NormalGreibachGrammar toGreibach() {

    SymbolContainer newSymbols = new SymbolContainer(getSymbols());
    ArrayList<NonterminalSymbol> baseNonterminals = newSymbols.getNonterminalsList();
    ProductionContainer newProductions = new ProductionContainer(getProductions());
    Collections.sort(baseNonterminals);

    for(NonterminalSymbol lhs : baseNonterminals) {
      ArrayList<Production> currProductions = newProductions.getProductionsForSymbol(lhs);

      for(int i = 0; i < currProductions.size(); i++) {
        Production curr = currProductions.get(i);

        if(!curr.getRhs()[0].isTerminal()) {
          NonterminalSymbol firstSymbol = (NonterminalSymbol)curr.getRhs()[0];
          NonterminalSymbol left = curr.getLhs();

          if(left.compareTo(firstSymbol) > 0) {
            for(Production production : newProductions.getProductionsForSymbol(firstSymbol)) {
              Production newProduction = curr.applyProductionToFirstSymbol(production);
              newProductions.add(newProduction);
              currProductions.add(newProduction);
            }
            newProductions.remove(currProductions.get(i));
          }
        }
      }

      currProductions = newProductions.getProductionsForSymbol(lhs);
      for(int i = 0; i < currProductions.size(); i++) {
        Production curr = currProductions.get(i);

        if(curr.ifRecursive()) {
          NonterminalSymbol s = newSymbols.createSymbol();
          newProductions.remove(curr);

          for(int j = 0; j < currProductions.size(); j++) {
            if(!currProductions.get(j).ifRecursive())
              newProductions.add(new Production(currProductions.get(j).getLhs(), currProductions.get(j).getRhs(), s));
          }

          Production p = new Production(s, curr.getSufix());
          newProductions.add(p);
          newProductions.add(new Production(s, p.getRhs(), s));

        }
      }
    }

    ArrayList<Production> greibach = newProductions.getGreibachProductions();
    for(int i = 0; i < greibach.size(); i++) {
      NonterminalSymbol s = greibach.get(i).getLhs();
      for(ArrayList<Production> productionsList : newProductions.getProductions().values()) {
        for(Production production : productionsList) {
          if(production.startsWith(s)) {
            greibach.add(production.applyProductionToFirstSymbol(greibach.get(i)));
          }
        }
      }
    }

    return new NormalGreibachGrammar(newSymbols, new ProductionContainer(greibach), getStartSymbol());
  }

}
