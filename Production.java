/* Grammar production representation
 * author : Marcin Gregorczyk
 */

package gramatyka;

public class Production {

  public Production(NonterminalSymbol left, Symbol[] right) {
    lhs = left;
    rhs = right;
  }

  public Production(NonterminalSymbol newLhs, Symbol[] newRhs, NonterminalSymbol s) {
    Symbol[] modRhs = new Symbol[newRhs.length + 1];
    for(int i = 0; i < newRhs.length; i++)
      modRhs[i] = newRhs[i];
    modRhs[modRhs.length - 1] = s;
    lhs = newLhs;
    rhs = modRhs;
  }

  public NonterminalSymbol getLhs() {
    return lhs;
  }

  public Symbol[] getRhs() {
    return rhs;
  }

  public Production applyProductionToFirstSymbol(Production production) {
    Symbol[] addition = production.getRhs();
    Symbol[] newRhs = new Symbol[addition.length + rhs.length-1];
    for(int i = 0; i < addition.length; i++)
      newRhs[i] = addition[i];
    for(int i = 1; i < rhs.length; i++) {
      newRhs[addition.length+i-1] = rhs[i];
    }
    return new Production(lhs, newRhs);
  }

  public boolean ifRecursive() {
    if(rhs.length > 0 && rhs[0] == lhs)
      return true;
    return false;
  }

  public boolean ifChomsky() {
    if(rhs.length == 1 && !rhs[0].isTerminal())
      return false;
    if(rhs.length == 2 && (rhs[0].isTerminal() || rhs[1].isTerminal()))
      return false;
    if(rhs.length != 1 && rhs.length != 2)
      return false;

    return true;
  }

  public boolean ifGreibach() {
    if(rhs.length == 0)
      return false;
    if(!rhs[0].isTerminal())
      return false;
    for(int i = 1; i < rhs.length; i++)
      if(rhs[i].isTerminal())
        return false;
    return true;
  }

  public boolean startsWith(Symbol s) {
    if(rhs.length == 0)
      return false;
    if(rhs[0] == s)
      return true;
    return false;
  }

  public String toString() {
    String result = lhs + " -> ";
    for(int i = 0; i < rhs.length; i++)
      result += rhs[i];
    if(rhs.length == 0)
      result += "&";
    return result;
  }

  protected Symbol[] getSufix() {
    if(rhs.length == 0)
      return null;
    Symbol[] result = new Symbol[rhs.length - 1];
    for(int i = 1; i < rhs.length; i++)
      result[i-1] = rhs[i];
    return result;
  }

  private NonterminalSymbol lhs;
  private Symbol[] rhs;

}
