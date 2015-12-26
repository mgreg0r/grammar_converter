/* Regular grammar
 * author : Marcin Gregorczyk
 */

package gramatyka;

public class RegularGrammar extends ContextFreeGrammar {

  public RegularGrammar(ContextFreeGrammar grammar) {
    super(grammar);
    if(!super.ifRegular())
      throw new GrammarException("Failed attempt to construct regular grammar");

  }

  @Override
  public String getGrammarType() {
    return "regular";
  }

}
