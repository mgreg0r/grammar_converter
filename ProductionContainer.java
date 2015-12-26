/* Container for grammar productions
 * author : Marcin Gregorczyk
 */


package gramatyka;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductionContainer {

  public ProductionContainer(ProductionContainer productionContainer) {
    productions = productionContainer.getProductions();
  }

  public ProductionContainer(ArrayList<Production> list) {
    for(Production p : list) {
      add(p);
    }
  }

  public ProductionContainer() {
    // Constructing empty container
  }

  public void add(Production production) {
    if(!productions.containsKey(production.getLhs()))
      productions.put(production.getLhs(), new ArrayList<Production>());
    productions.get(production.getLhs()).add(production);
  }

  public void remove(Production production) {
    ArrayList<Production> l = productions.get(production.getLhs());
    l.remove(production);
  }

  public ArrayList<Production> getGreibachProductions() {
    ArrayList<Production> result = new ArrayList<>();
    for(ArrayList<Production> l : productions.values()) {
      for(Production p : l) {
        if(p.ifGreibach())
          result.add(p);
      }
    }
    return result;
  }

  public String toString() {
    String result = "";
    for(ArrayList<Production> productionsList : productions.values()) {
      for(Production production : productionsList) {
        result += production + "\n";
      }
    }
    return result;
  }

  public ArrayList<Production> getProductionsForSymbol(NonterminalSymbol s) {
    if(!productions.containsKey(s))
      return new ArrayList<Production>();
    return new ArrayList<>(productions.get(s));
  }

  public HashMap<NonterminalSymbol, ArrayList<Production>> getProductions() {
    HashMap<NonterminalSymbol, ArrayList<Production>> result = new HashMap<>();
    for(NonterminalSymbol s : productions.keySet()) {
      ArrayList<Production> l = new ArrayList<>(productions.get(s));
      result.put(s, l);
    }
    return result;
  }

  private HashMap<NonterminalSymbol, ArrayList<Production>> productions = new HashMap<>();

}
