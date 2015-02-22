/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * 172ページの7.10節「Javaクラスの拡張とJavaインターフェースの実装」の終わりでは、
 * addに対するすべての呼び出しをログに記憶するようにArrayListを拡張する方法を説明しました。
 * しかし、それは、単一オブジェクトに対してのみ機能します。
 * ログを記憶するArrayListを何個でも生成できるようにするために、そのような複数オブジェクトを生成する
 * ファクトリとなるJavaScript関数を書きなさい。
 */
/*
 * A.
 * - log
 * Adding Hoge
 * Adding Foo
 * [Hoge, Foo]
 * Adding Test_1
 * Adding Test_2
 * [Test_1, Test_2]
 */

function getLoggingList(){
	var arr = new (Java.extend(java.util.ArrayList)) {
		add: function(x) {
			print('Adding ' + x);
			return Java.super(arr).add(x)
		}
	}
	return arr
}

var arr = getLoggingList()
arr.add('Hoge')
arr.add('Foo')
print(arr)

var arr2 = getLoggingList()
arr2.add('Test_1')
arr2.add('Test_2')
print(arr2)