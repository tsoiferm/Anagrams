import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

class AnagramsTest {

	@Test
	void dictAndMaxEntTest() {
		Anagrams a = new Anagrams();
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		assertEquals("[236204078=[alerts, alters, artels, estral, laster, lastre, rastle, ratels, relast, resalt, salter, slater, staler, stelar, talers]]", maxEntries.toString());
	}
	
	@Test
	void multiMaxAndAddWordTest() {
		Anagrams a = new Anagrams();
		a.addWord("butlers");
		a.addWord("lrsetbu");
		a.addWord("tbuselr");
		a.addWord("lserbut");
		a.addWord("flap");
		a.addWord("palf");
		a.addWord("falp");
		a.addWord("lafp");
		a.addWord("bat");
		a.addWord("tab");
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		assertEquals("[25864346541=[butlers, lrsetbu, tbuselr, lserbut], 50986=[flap, palf, falp, lafp]]", maxEntries.toString());
	}
	
	@Test
	void buildLetterTableTest() {
		Anagrams a = new Anagrams();
		assertEquals("{a=2, b=3, c=5, d=7, e=11, f=13, g=17, h=19, i=23, j=29, k=31, l=37, m=41, n=43, o=47, p=53, q=59, r=61, s=67, t=71, u=73, v=79, w=83, x=89, y=97, z=101}", a.letterTable.toString());
	}
	
	@Test
	void myHashCodeTest() {
		Anagrams a = new Anagrams();
		assertEquals(a.myHashCode("alters"),a.myHashCode("lsrtea"));
	}
	@Test
	void myHashCodeTest2() {
		Anagrams a = new Anagrams();
		assertEquals(a.myHashCode("asdfghjkl"),a.myHashCode("adgjlsfhk"));
	}
	
	@Test
	void addAndMultiMaxTest2() {
		Anagrams a = new Anagrams();
		a.addWord("hjk");
		a.addWord("ert");
		a.addWord("jhk");
		a.addWord("kjh");
		a.addWord("ter");
		a.addWord("ret");
		a.addWord("falp");
		a.addWord("lafp");
		a.addWord("plaf");
		a.addWord("tab");
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		assertEquals("[17081=[hjk, jhk, kjh], 47641=[ert, ter, ret], 50986=[falp, lafp, plaf]]"
				+ "", maxEntries.toString());
	}
}
