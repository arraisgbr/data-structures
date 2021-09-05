public class GetWords {

  public static String[] words(String s) {
  	String r = "çÇáéíóúýÁÉÍÓÚÝàèìòùÀÈÌÒÙãõñäëïöüÿÄËÏÖÜÃÕÑâêîôûÂÊÎÔÛ";
  	s = s.replaceAll("[^A-Za-z" + r + "\\s]", " ");
  	s = s.trim();
  	String[] w = s.split("\\s+");
  	return w;
  }

  public static void main(String[] args) {
  	String s = StdIn.readAll();
  	String[] w = words(s);
  	for (String t : w) StdOut.println(t);
  }
}
