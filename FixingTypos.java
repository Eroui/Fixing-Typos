import java.util.*;
import java.io.*;


public class FixingTypos {

	private static String DEFAULT_FILE = "inputFile.in";
	public static void main(String[] args) {
		try {

			File inputFile = new File(args.length > 0 ? args[0] : DEFAULT_FILE);
			FileTypoFixer fixer = new FileTypoFixer(inputFile);

			fixer.fixTypos();

		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}


class FileTypoFixer {	
	public File file;
	Scanner reader;

	public FileTypoFixer(File f) throws Exception {
		this.file = f;
		reader = new Scanner(f);
	}

	public void fixTypos() {
		while(reader.hasNext()) {
			String line = reader.nextLine();
			String[] tokens = line.split("\\s+");

			boolean first = true;
			for(String s : tokens) {
				if(!first)
					System.out.print(" ");
				first = false;
				System.out.print(fixWord(s));
			}
			System.out.println();
		}
	}


	public String fixWord(String s) {
		char[] word = s.toCharArray();
		char[] fixd = new char[word.length];
		int cur = 0;
		for (int i = 0; i < word.length; ++i) {
			if (cur < 2) fixd[cur++] = word[i];
			else {
				if (fixd[cur - 1] == word[i] && word[i] == fixd[cur - 2])
					continue;
				if (fixd[cur - 1] == word[i]) {
					if (cur < 3) fixd[cur++] = word[i];
					else {
						char c1 = fixd[cur - 3];
						char c2 = fixd[cur - 2];
						if (c1 == c2) continue;
						else fixd[cur++] = word[i];
					}
				} else
				fixd[cur++] = word[i];
			}
		}

		return (new String(fixd)).trim();
	}
	
}