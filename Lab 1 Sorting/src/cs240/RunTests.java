package cs240;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * 
 * A class to run simple tests on the sorting algorithms.
 * @author Eli Zupke
 *
 */
public class RunTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*int[] list = {256, 288, 83, 265, 167, 82, 78, 275, 214, 56, 158, 235, 192, 111, 45, 41, 28, 171, 176, 71, 249, 278, 23, 299, 70, 206, 212, 141, 271, 118, 93, 131, 106, 195, 12, 253, 200, 36, 215, 2, 150, 204, 129, 210, 250, 238, 291, 169, 133, 85, 233, 107, 123, 205, 232, 105, 29, 174, 87, 128, 127, 177, 72, 225, 21, 196, 42, 101, 194, 79, 17, 152, 121, 124, 273, 22, 5, 144, 31, 159, 228, 227, 252, 268, 113, 292, 255, 54, 1, 4, 164, 146, 251, 84, 6, 91, 102, 289, 58, 62, 16, 53, 165, 189, 18, 50, 119, 100, 30, 277, 55, 257, 226, 61, 293, 38, 294, 122, 290, 281, 151, 51, 279, 300, 27, 259, 75, 198, 170, 104, 90, 188, 286, 191, 283, 48, 203, 246, 116, 143, 15, 172, 242, 181, 153, 219, 145, 295, 148, 32, 103, 109, 284, 173, 67, 182, 37, 7, 301, 47, 8, 186, 86, 263, 40, 120, 138, 125, 190, 19, 243, 25, 9, 236, 114, 39, 154, 297, 202, 209, 234, 168, 239, 11, 161, 218, 221, 231, 217, 142, 108, 99, 57, 126, 185, 94, 89, 193, 110, 199, 287, 115, 266, 96, 272, 98, 14, 207, 187, 43, 208, 270, 216, 183, 88, 264, 136, 237, 59, 262, 64, 26, 46, 130, 298, 63, 112, 3, 149, 13, 260, 97, 49, 230, 77, 76, 160, 220, 10, 179, 261, 276, 74, 223, 137, 241, 163, 132, 285, 134, 117, 20, 197, 280, 65, 147, 244, 81, 201, 254, 34, 135, 33, 282, 240, 140, 178, 229, 95, 73, 35, 155, 157, 69, 247, 66, 166, 258, 211, 184, 296, 224, 52, 248, 213, 175, 274, 222, 267, 269, 92, 156, 24, 68, 162, 245, 60, 180, 44, 80};
		list = RecursiveShellSort.sort(list);
		Utilities.printArray(list);*/
		for(int i = 10; i < 1000; i += 10) {
			loggedTests(i);
		}
	}
	
	public static void loggedTests(int n) {
		double avgMove = 0;
		double avgCompare = 0;
		int count = 100;
		for (int i = 0; i < count; i++) {
			int[] r = LoggedIterativeShellSort.sort(randomArray(n));
			avgMove += r[0];
			avgCompare += r[1];
		}
		
		avgMove /=count;
		avgCompare /= count;
		log("Shell", n, avgMove, avgCompare);
	}
	
	
	
	public static int[] randomArray(int len) {
		Random r = new Random();
		int[] a = new int[len];
		
		for (int i = 0; i < len; i++) {
			a[i] = r.nextInt(999);
		}
		
		return a;
	}
	
	public static void log(String sort, int length, double avgMove, double avgCompare) {
		
		FileWriter fw;
		try {
			fw = new FileWriter("/home/eli/Documents/Cal Poly Pomona/2017F/CS 240-02/Graph Gen/sort2.csv", true);
			
			fw.write(sort + "," + length + "," + avgMove + "," + avgCompare + "\n");
			
			
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
