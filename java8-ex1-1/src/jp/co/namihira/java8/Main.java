/**
 * Q.
 * Arrays.sortメソッド内で呼び出させるコンパレータのコードは、
 * sortメソッドを呼び出したスレッドで実行されるのでしょうか？それとも、別のスレッドで実行されるでしょうか？
 */
/**
 * A.同じスレッドで実行される
 * mainThreadName : main
 * comparatorThreadName : main
 */

package jp.co.namihira.java8;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
	    System.out.println("mainThreadName : " + Thread.currentThread().getName());

	    String[] words = new String[]{"bb", "ccc", "a"};

	    Arrays.sort(words, (first, second) -> {
	        System.out.println("comparatorThreadName : " + Thread.currentThread().getName());
	        return Integer.compare(first.length(), second.length());
	    });
	}

}
