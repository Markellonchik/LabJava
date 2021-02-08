import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	private static final String path = "file.txt";
	public static void main(String[] args) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e1) {
			System.out.println("Could not find file");
			e1.printStackTrace();
		}
		String line;
		List<String> words = new ArrayList<String>();
		Set<String> longestWords = new TreeSet<String>();
		
		int maxConsonantSubstring = 0;

		try {
			while((line = reader.readLine()) != null) {
				for(int i = 0; i < line.length(); ++i) {
					char c = line.charAt(i);
					if(!isWordLetter(c)) continue;

					int j = i;
					while(j < line.length() && isWordLetter(line.charAt(j))) {
						j++;
					}
					if(j - i > 28) {
						i = j - 1;
						continue;
					}
					words.add(line.substring(i, j));
					int subLength = longestVowelSubstring(words.get(words.size() - 1));
					if(subLength > maxConsonantSubstring) {
						maxConsonantSubstring = subLength;
						longestWords.clear();
						longestWords.add(words.get(words.size() - 1));
					}
					else if(subLength == maxConsonantSubstring) {
						longestWords.add(words.get(words.size() - 1));
					}
					i = j - 1;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("All words count: " + words.size());
		System.out.println("Longest consonant substring: " + maxConsonantSubstring);
		System.out.println("Longest words count: " + longestWords.size());
		for(String str : longestWords) {
			System.out.println(str);
		}
	}
	public static boolean isWordLetter(char c) {
		return (Character.isLetter(c) || c == '-' || c == '\'');
	}
	public static boolean isConsonant(char c) {
		return Character.isLetter(c) && 
			   (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u' &&
			    c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U');
	}
	public static int longestVowelSubstring(String s) {
		int mx = 0;
		for(int i = 0, curCnt = 0; i < s.length(); ++i) {
			if(isConsonant(s.charAt(i))) curCnt++;
			else curCnt = 0;
			mx = Math.max(mx, curCnt);
		}
		return mx;
	}
}
