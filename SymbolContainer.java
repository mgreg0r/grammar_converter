/* Container for alphabet symbols
 * author : Marcin Gregorczyk
 */

package gramatyka;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolContainer {

  public SymbolContainer(String newTerminals, String newNonterminals) {
    for(char c : newTerminals.toCharArray())
      addTerminal(c);

    for(char c : newNonterminals.toCharArray())
      addNonterminal(c);
  }

  public SymbolContainer(SymbolContainer model) {
    terminals = model.getTerminals();
    nonterminals = model.getNonterminals();
  }

  public SymbolContainer() {
    // Constructing empty container
  }

  public TerminalSymbol getTerminalByChar(char c) {
    return terminals.get(c);
  }

  public NonterminalSymbol getNonterminalByChar(char c) {
    return nonterminals.get(c);
  }

  public void addTerminal(char c) {
    if(c < 'a' || c > 'z')
      throw new GrammarException("Illegal terminal symbol: " + c);
    if(terminals.containsKey(c))
      throw new GrammarException("Terminal symbol " + c + " is not unique");

    TerminalSymbol s = new TerminalSymbol(c);
    terminals.put(c, s);
  }

  public void addNonterminal(char c) {
    if(c < 'A' || c > 'Z')
      throw new GrammarException("Illegal nonterminal symbol: " + c);
    if(nonterminals.containsKey(c))
      throw new GrammarException("Nonterminal symbol " + c + " is not unique");

    NonterminalSymbol s = new NonterminalSymbol(c);
    nonterminals.put(c, s);
  }

  public NonterminalSymbol createSymbol() {
    for(char c = 'A'; c <= 'Z'; c++) {
      if(!nonterminals.containsKey(c)) {
        NonterminalSymbol s = new NonterminalSymbol(c);
        nonterminals.put(c, s);
        return s;
      }
    }
    throw new GrammarException("Couldn't create new nonterminal symbol");
  }

  public String toString() {
    String result = "Terminals: ";
    for(char c : terminals.keySet()) {
      result += c;
    }
    result += "\n";
    result += "Nonterminals: ";
    for(char c : nonterminals.keySet()) {
      result += c;
    }
    return result;
  }

  public HashMap<Character, TerminalSymbol> getTerminals() {
    return new HashMap<>(terminals);
  }

  public HashMap<Character, NonterminalSymbol> getNonterminals() {
    return new HashMap<>(nonterminals);
  }

  public ArrayList<NonterminalSymbol> getNonterminalsList() {
    ArrayList<NonterminalSymbol> result = new ArrayList<>();
    for(NonterminalSymbol s : nonterminals.values())
      result.add(s);
    return result;
  }

  private HashMap<Character, TerminalSymbol> terminals = new HashMap<>();
  private HashMap<Character, NonterminalSymbol> nonterminals = new HashMap<>();

}
