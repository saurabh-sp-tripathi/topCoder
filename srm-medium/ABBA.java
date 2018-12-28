// solution to https://arena.topcoder.com/#/u/practiceCode/16527/48825/13918/2/326683 

public class ABBA {

	@Test
	public void testIfEvenB() {
		String t = "BBBAAABBAABBBAABBAA";
	}

	@Test
	public void test() {
		String target = "123456890456";
		String init = "456";
		System.out.println(target.substring(0, target.indexOf(init)));
		System.out.println(target.substring(target.indexOf(init) + init.length()));
		System.out.printf("target.length - 3 = %d , index = %d", target.length() - 3, target.indexOf("890"));
		System.out.printf("\n1st : %d ; 2nd : %d", target.indexOf("456"), target.indexOf("456", target.indexOf("456") + 1));
	}

	@Test
	public void testABBA() {
		for (int i = 0; i < 20; i++) {
			String init = createRandomABBA("", (int) (Math.random() * 10));
			String revInit = new StringBuffer(init).reverse().toString();
			String target = createRandomABBA(init, (int) (Math.random() * 10));

/*
			init = "BAAAAABBA";
			target = "BAABBAAAAABAAA";
*/

			canObtain(init, target);
		}
	}

	public String canObtain(String initial, String target) {

		boolean f = false;
		boolean rev = false;
		int initIndex = target.indexOf(initial);
		int bDiff = countB(target) - countB(initial);
		if (bDiff < 0) {
			System.out.println("Impossible");
		} else {
			if (bDiff % 2 != 0) {
				initial = new StringBuffer(initial).reverse().toString();
				initIndex = target.indexOf(initial);
				rev = true;
			}
		}
		while (initIndex <= target.length() - initial.length() && initIndex != -1) {
			int bLeft = 0;
			if (initIndex != 0) {
				bLeft = countB(target.substring(0, initIndex));
			}
			int bRight = 0;
			if (initIndex < target.length() - initial.length()) {
				bRight = countB(target.substring(initIndex + initial.length()));
			}
			if (bLeft == bRight && !rev) {
				f = true;
				initIndex = -1;
			} else if (bLeft > bRight && rev) {
				f = true;
				initIndex = -1;
			} else {
				System.out.println("Impossible : " + initIndex);
				initIndex = target.indexOf(initial, initIndex + 1);
				System.out.println("initindex : " + initIndex);
			}
		}
		String result = f ? "Possible" : "Impossible";
		System.out.printf("\nfor init : %s random string: %s, result : %s\n", initial, target, result);
		return result;
	}


	public int countB(String target) {
		return target.length() - target.replace("B", "").length();
	}

	public String createRandomABBA(String init, int len) {
		StringBuffer sb = new StringBuffer();
		sb.append(init);
		for (int i = 0; i < len; i++) {
			int random = (int) (Math.random() * 10);
			//System.out.printf("\nrandom: %d", random);
			if (random % 2 == 0) {
				sb.append("A");
			} else {
				sb.append("B");
				sb.reverse();
			}
		}
		return sb.toString();
	}

	@Test
	public void testReverseString() {
		StringBuffer sb = new StringBuffer();
		sb.append("123 456 789");
		System.out.printf("reverse 12345 789 54321 is %s", sb.reverse().toString()); //reverseString("12345 789 54321"));
	}

	public String reverseString(String real) {
		//convert string to character array
		char[] strArr = real.toCharArray();

		//create a new array which will contain characters in reversed order
		char[] revArr = new char[strArr.length];

		for (int i = strArr.length, j = 0; i > 0; i--, j++) {
			revArr[j] = strArr[i - 1];
		}

		String revStr = new String(revArr);
		return revStr;
	}

}
