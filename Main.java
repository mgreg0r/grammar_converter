/* Example use of grammar package
 * author : Marcin Gregorczyk
 */

package gramatyka;

public class Main {

  public static void main(String[] args) {
    
    String t1 = "abc";
    String n1 = "ABC";
    String[][] p1 = {{"a", "aB"}, {"BBBC"}, {"", "cBc", "aCa"}};
    
    String t2 = "g";
    String n2 = "XY";
    String[][] p2 = {{"", "g"}, {"g", "gX"}};
    
    String t3 = "a";
    String n3 = "ABCT";
    String[][] p3 = {{"a"}, {"aT"}, {"aCB"}, {"aABC"}};
    
    String t4 = "a";
    String n4 = "ABC";
    String[][] p4 = {{"BC"}, {"CA"}, {"a", "BA"}};
    
    System.out.println("TEST 1 - CONTEXT FREE GRAMMAR");
    ContextFreeGrammar g1 = new ContextFreeGrammar(t1, n1, p1);
    testGrammar(g1);
    
    System.out.println("TEST 2 - REGULAR GRAMMAR AS CONTEXT FREE");
    ContextFreeGrammar g2 = new ContextFreeGrammar(t2, n2, p2);
    testGrammar(g2);
    System.out.println("CREATING REGULAR GRAMMAR FROM A REFERENCE");
    RegularGrammar r2 = new RegularGrammar(g2);
    testGrammar(r2);
    
    System.out.println("TEST 3 - GREIBACH GRAMMAR AS CONTEXT FREE");
    ContextFreeGrammar g3 = new ContextFreeGrammar(t3, n3, p3);
    testGrammar(g3);
    System.out.println("CREATING GREIBACH GRAMMAR FROM A REFERENCE");
    NormalGreibachGrammar gr3 = new NormalGreibachGrammar(g3);
    testGrammar(gr3);
    
    System.out.println("TEST 4 - CHOMSKY GRAMMAR AS CONTEXT FREE");
    ContextFreeGrammar g4 = new ContextFreeGrammar(t4, n4, p4);
    testGrammar(g4);
    System.out.println("CREATING CHOMSKY GRAMMAR FROM A REFERENCE");
    NormalChomskyGrammar c4 = new NormalChomskyGrammar(g4);
    testGrammar(c4);
    System.out.println("CONVERSION TO GREIBACH");
    NormalGreibachGrammar gr4 = c4.toGreibach();
    testGrammar(gr4);
    
  }

  public static void testGrammar(ContextFreeGrammar g) {
    System.out.println(g);
    System.out.println("PROPERTIES OF CREATED GRAMMAR :");
    if(g.ifRegular())
      System.out.println("ifRegular() == true");
    if(g.ifGreibach())
      System.out.println("ifGreibach() == true");
    if(g.ifChomsky())
      System.out.println("ifChomsky() == true");
  }

}
