package new_lzw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class compress {
	String h;
	String search = "";

	ArrayList<String> arr_s = new ArrayList<String>();
	ArrayList<Integer> arr_t = new ArrayList<Integer>();

	ArrayList<String> arr_s1 = new ArrayList<String>();
	ArrayList<Integer> arr_t1 = new ArrayList<Integer>();

	public void ascii1() {
		char e;
		String s = "";
		for (int i = 32; i < 128; i++) {
			e = (char) i;
			s += e;
			arr_t.add(i);
			arr_s.add(s);
			s = "";
		}
	}

	public void ascii2() { // for decompresion
		char y;
		String s1 = "";
		for (int i = 32; i < 128; i++) {
			y = (char) i;
			s1 += y;
			arr_t1.add(i);
			arr_s1.add(s1);
			s1 = "";
		}
	}

	String readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));

		StringBuilder sb = new StringBuilder();
		String line = br.readLine();

		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}
		h = sb.toString();
		// System.out.println(h.length());
		return h;

	}

	public void compress(String p2) throws IOException {
		readFile(p2);
		System.out.println(h);
		String temp = "";
		char c;
		int i = 0;
		int idx = 128;
		int tempindex;
		int index = 0;
		String total = "";
		int size = h.length();
		System.out.println(size);

		ascii1();
		while (i < size) {
			c = h.charAt(i);
			temp += c;

			while (arr_s.contains(temp)) {
				if (i == (size - 1)) {
					tempindex = arr_s.indexOf(temp);
					index = arr_t.get(tempindex);
					total += index;
					System.out.println(total);

					PrintWriter out = new PrintWriter("c1.txt");
					out.println(total);
					out.close();
					return;
				}

				i++;
				c = h.charAt(i);
				temp += c;
			}

			// we are out of the small while loop

			// System.out.print(temp);
			String t = temp.substring(0, temp.length() - 1);

			// System.out.print(t);
			tempindex = arr_s.indexOf(t); // index of t=A when temp=AB at the
											// arr_s (dictionary)

			// System.out.print(tempindex);
			index = arr_t.get(tempindex); // index of t=A when temp=AB at the
											// arr_t (index)

			// System.out.print(index);
			arr_s.add(temp); // temp mesh mawgoda fel arr_s 3alshan keda bahotha

			arr_t.add(idx); // index 128 fel arr_t
			total += index + ",";
			// System.out.print(idx);
			idx++;
			// System.out.print(j);
			temp = "";
		}
		// out of the big loop
	}

	// takhod kol el arkam men el file w tohtha fe arraylist

	public ArrayList readfcofile(String path) {
		File f1 = new File(path);
		ArrayList<Integer> inarr = new ArrayList<Integer>();
		String d1 = "";
		int x;
		char g;
		try {
			Scanner in = new Scanner(f1);
			while (in.hasNext()) {
				search += in.next();
			}
			in.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(compress.class.getName()).log(Level.SEVERE, null, ex);
		}
		for (int i = 0; i < search.length(); i++) {
			g = search.charAt(i);
			if (g == ',') {
				x = Integer.parseInt(d1);
				inarr.add(x);
				d1 = "";
			} else if (i == search.length() - 1) {
				d1 += g;
				x = Integer.parseInt(d1);
				inarr.add(x);
			} else {
				d1 += g;
			}
		}
		// System.out.print(inarr);
		return inarr;
	}

	public void decompress(String path) {

		compress s1 = new compress();
		ascii2();

		ArrayList<Integer> arr0 = new ArrayList<Integer>(); // take all indices
															// from the file in
															// it .

		arr0 = readfcofile(path);

		// System.out.println(nbv.size());
		// System.out.println(nbv);

		int tempindex;
		char s4;
		int idx = 128;

		String last = "";
		String total = "";
		String index = "";
		String c1 = "";
		String c2 = "";

		for (int i = 0; i < arr0.size(); i++) {
			if (i == 0) {
				tempindex = arr_t1.indexOf(arr0.get(i)); // get the value of 65
															// from the index
															// arr
				index = arr_s1.get(tempindex); // get the char of thee tempindex
												// from dictionary arr
				total += index; // collect the chars together in a string
				c1 = index;
				last = c1;
				// System.out.println(total);
			} else {
				if (!arr_t1.contains(arr0.get(i))) {
					c2 = last + last.substring(0, 1);
					arr_s1.add(c2);
					arr_t1.add(arr0.get(i));
					total += c2;
					c1 = c2;
					last = c1;
					idx++;
					// System.out.println(total);
				} else {
					tempindex = arr_t1.indexOf(arr0.get(i));
					index = arr_s1.get(tempindex);
					total += index;
					String z = last + index.substring(0, 1);
					arr_s1.add(z);
					arr_t1.add(idx);
					c1 = index;
					last = c1;
					idx++;
					// System.out.println(total);
				}
			}
		}
		System.out.print(total);

	}

}
