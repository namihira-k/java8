/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 *  Collectionsクラスのメソッドに目を通してください。みなさんが、何でもできるとしたら、どのインタフェースにCollectionsクラスの各メソッドを入れますか。
 *  それぞれ、デフォルトメソッドになりますか？それとも、staticメソッドになりますか。
 */
/**
 * A.
 *
 * デフォルトメソッド：インスタンスメソッドとして使う場合。（基本的にこちらを選択）
 * staticメソッド：ファクトリ系のメソッドとして使う場合。
 *
 * 以下、Collectionsクラスのメソッド一覧。

static <T> boolean addAll(Collection<? super T> c, T... a)
         指定されたすべての要素を指定されたコレクションに追加します。
    ->いらない。CollectionインタフェースのaddAll()で十分。

static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key)
         バイナリサーチアルゴリズムを使用して、指定されたリストから指定されたオブジェクトを検索します。
　　　-> Listインタフェースに欲しい。
    -> default <? extends Comparable<? super T>> int binarySearch(T key)

static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c)
         バイナリサーチアルゴリズムを使用して、指定されたリストから指定されたオブジェクトを検索します。
　　　-> Listインタフェースに欲しい。
    -> default <? extends T> int binarySearch(T key, Comparator<? super T> c)

static <E> Collection<E> checkedCollection(Collection<E> c, Class<E> type)
         指定されたコレクションの、動的に型保証されたビューを返します。
　　　-> Collectionインタフェースに欲しい。
    -> static <E> Collection<E> checkedCollection(Collection<E> c, Class<E> type)

static <E> List<E> checkedList(List<E> list, Class<E> type)
         指定されたリストの動的に型保証されたビューを返します。
　　　-> Listインタフェースに欲しい。
    -> static <E> List<E> checkedList(List<E> list, Class<E> type)

static <K,V> Map<K,V> checkedMap(Map<K,V> m, Class<K> keyType, Class<V> valueType)
         指定されたマップの動的に型保証されたビューを返します。
　　　-> Mapインタフェースに欲しい。
    -> static <K,V> Map<K,V> checkedMap(Map<K,V> m, Class<K> keyType, Class<V> valueType)

static <E> Set<E> checkedSet(Set<E> s, Class<E> type)
         指定されたセットの動的に型保証されたビューを返します。
　　　-> Setインタフェースに欲しい。
    -> static <E> Set<E> checkedSet(Set<E> s, Class<E> type)

static <K,V> SortedMap<K,V> checkedSortedMap(SortedMap<K,V> m, Class<K> keyType, Class<V> valueType)
         指定されたソートマップの動的に型保証されたビューを返します。
　　　-> SortedMapインタフェースに欲しい。
    -> static <K,V> SortedMap<K,V> checkedSortedMap(SortedMap<K,V> m, Class<K> keyType, Class<V> valueType)

static <E> SortedSet<E> checkedSortedSet(SortedSet<E> s, Class<E> type)
         指定されたソートセットの動的に型保証されたビューを返します。
　　　-> SortedMapインタフェースに欲しい。
    -> static <E> SortedSet<E> checkedSortedSet(SortedSet<E> s, Class<E> type)

static <T> void copy(List<? super T> dest, List<? extends T> src)
         あるリストから別のリストにすべての要素をコピーします。
　　　-> Listインタフェースに欲しい。
    -> static <T> void copy(List<? super T> dest, List<? extends T> src)

static boolean disjoint(Collection<?> c1, Collection<?> c2)
         指定された 2 つのコレクションに共通の要素が存在しない場合、true を返します。
　　　-> Collectionインタフェースに欲しい。
    -> static boolean disjoint(Collection<?> c1, Collection<?> c2)

static <T> List<T> emptyList()
         空のリストを返します (不変)。
　　　-> Listインタフェースに欲しい。
    -> static <T> List<T> emptyList()

static <K,V> Map<K,V> emptyMap()
         空のマップを返します (不変)。
　　　-> Mapインタフェースに欲しい。
    -> static <K,V> Map<K,V> emptyMap()

static <T> Set<T> emptySet()
         空のセット (不変) を返します。
　　　-> Setインタフェースに欲しい。
    -> static <T> Set<T> emptySet()

static <T> Enumeration<T> enumeration(Collection<T> c)
         指定されたコレクションの列挙を返します。
　　　-> Collectionインタフェースに欲しい。
    -> default <T> Enumeration<T> enumeration()

static <T> void fill(List<? super T> list, T obj)
         指定されたリストのすべての要素を指定された要素で置き換えます。
　　　-> Listインタフェースに欲しい。
    -> default <T> void fill(T obj)

static int frequency(Collection<?> c, Object o)
         指定されたコレクション内で、指定されたオブジェクトと等価な要素の数を返します。
　　　-> Collectionインタフェースに欲しい。
    -> default int frequency(Object o)

static int indexOfSubList(List<?> source, List<?> target)
         指定されたソースリスト内で、指定されたターゲットリストが最初に出現した位置の開始位置を返します。
　　　-> Listインタフェースに欲しい。
    -> default int indexOfSubList(List<?> target)

static int lastIndexOfSubList(List<?> source, List<?> target)
         指定されたソースリスト内で、最後に出現した指定ターゲットリストの開始位置を返します。
　　　-> Listインタフェースに欲しい。
    -> default int lastIndexOfSubList(List<?> target)

static <T> ArrayList<T> list(Enumeration<T> e)
         指定された列挙により返された要素を含む配列リストを、返された順番で返します。
　　　-> Enumerationインタフェースに欲しい。
    -> default <T> ArrayList<T> list()

static <T extends Object & Comparable<? super T>>　T max(Collection<? extends T> coll)
         要素の「自然順序付け」に従って、指定されたコレクションの最大の要素を返します。
　　　-> Collectionインタフェースに欲しい。
    -> default <T extends Object & Comparable<? super T>>　T max()

static　<T> T　max(Collection<? extends T> coll, Comparator<? super T> comp)
         指定されたコンパレータが示す順序に従って、指定されたコレクションの最大の要素を返します。
　　　-> Collectionインタフェースに欲しい。
    -> default <T> T　max(Comparator<? super T> comp)

static　<T extends Object & Comparable<? super T>>　T min(Collection<? extends T> coll)
         要素の「自然順序付け」に従って、指定されたコレクションの最小の要素を返します。
　　　-> Collectionインタフェースに欲しい。
    -> default <T> T　min()

static　<T> T　min(Collection<? extends T> coll, Comparator<? super T> comp)
         指定されたコンパレータが示す順序に従って、指定されたコレクションの最小の要素を返します。
　　　-> Collectionインタフェースに欲しい。
    -> default <T> T　min(Comparator<? super T> comp)

static　<T> List<T> nCopies(int n, T o)
         指定されたオブジェクトの n 個のコピーで構成される不変のリストを返します。
　　　-> Listインタフェースに欲しい。
    -> static　<T> List<T> nCopies(int n, T o)

static　<T> boolean replaceAll(List<T> list, T oldVal, T newVal)
         リスト内に出現する指定された値をすべて他の値に置き換えます。
         指定されたオブジェクトの n 個のコピーで構成される不変のリストを返します。
　　　-> Listインタフェースに欲しい。
    -> default <T> boolean replaceAll(T oldVal, T newVal)

static void reverse(List<?> list)
         指定されたリストの要素の順序を逆にします。
　　　-> Listインタフェースに欲しい。
    -> default void reverse()

static　<T> Comparator<T>　reverseOrder()
　　　Comparable インタフェースを実装するオブジェクトのコレクションで「自然順序付け」の逆を義務付けるコンパレータを返します。
　　　-> Comparatorインタフェースに欲しい。
    -> static　<T> Comparator<T>　reverseOrder()

static　<T> Comparator<T>　reverseOrder(Comparator<T> cmp)
   　　指定されたコンパレータの逆順を義務付けるコンパレータを返します。
　　　-> Comparatorインタフェースに欲しい。
    -> static　<T> Comparator<T>　reverseOrder(Comparator<T> cmp)

static void rotate(List<?> list, int distance)
         指定されたリストの要素を、指定された距離により回転します。
　　　-> Listインタフェースに欲しい。
    -> default void rotate(int distance)

static void shuffle(List<?> list)
         デフォルトの乱数発生の元を使用して、指定されたリストの順序を無作為に入れ替えます。
　　　-> Listインタフェースに欲しい。
    -> default void shuffle(List<?> list)

static void shuffle(List<?> list, Random rnd)
         デフォルトの乱数発生の元を使用して、指定されたリストの順序を無作為に入れ替えます。
　　　-> Listインタフェースに欲しい。
    -> default void shuffle(Random rnd)

static　<T> Set<T>　singleton(T o)
         指定されたオブジェクトだけを格納している不変のセットを返します。
　　　-> Setインタフェースに欲しい。
    -> static　<T> Set<T>　singleton(T o)

static　<T> List<T>　singletonList(T o)
         指定されたオブジェクトだけを格納している不変のリストを返します。
　　　-> Listインタフェースに欲しい。
    -> static　<T> List<T>　singletonList(T o)

static　<K,V> Map<K,V>　singletonMap(K key, V value)
         指定された値に指定されたオブジェクトだけをマッピングする不変のマップを返します。
　　　-> Mapインタフェースに欲しい。
    -> static　<K,V> Map<K,V>　singletonMap(K key, V value)

static　<T extends Comparable<? super T>>　void　sort(List<T> list)
         要素の「自然順序付け」に従って、指定されたリストを昇順にソートします。
　　　-> Listインタフェースに欲しい。
    -> default　<T extends Comparable<? super T>>　void　sort()

static　<T> void　sort(List<T> list, Comparator<? super T> c)
         指定されたコンパレータが示す順序に従って、指定されたリストをソートします。
　　　-> Listインタフェースに欲しい。
    -> default　<T> void　sort(List<T> list, Comparator<? super T> c)

static void swap(List<?> list, int i, int j)
         指定されたリストの指定された位置にある要素をスワップします。
　　　-> Listインタフェースに欲しい。
    -> default　void swap(int i, int j)

static　<T> Collection<T>　synchronizedCollection(Collection<T> c)
         指定されたコレクションを基にする同期 (スレッドセーフな) コレクションを返します。
　　　-> Collectionインタフェースに欲しい。
    -> default　<T> Collection<T>　synchronizedCollection()

static　<T> List<T>　synchronizedList(List<T> list)
         指定されたリストを基にする同期 (スレッドセーフな) リストを返します。
　　　-> Listインタフェースに欲しい。
    -> default　<T> List<T>　synchronizedList()

static　<K,V> Map<K,V>　synchronizedMap(Map<K,V> m)
         指定されたマップを基にする同期 (スレッドセーフな) マップを返します。
　　　-> Mapインタフェースに欲しい。
    -> default　<K,V> Map<K,V>　synchronizedMap()

static　<T> Set<T>　synchronizedSet(Set<T> s)
         指定されたセットを基にする同期 (スレッドセーフな) セットを返します。
　　　-> Setインタフェースに欲しい。
    -> default　<T> Set<T>　synchronizedSet()

static　<K,V> SortedMap<K,V>　synchronizedSortedMap(SortedMap<K,V> m)
         指定されたソートマップを基にする同期 (スレッドセーフな) ソートマップを返します。
　　　-> SortedMapインタフェースに欲しい。
    -> default　<K,V> SortedMap<K,V>　synchronizedSortedMap()

static　<T> SortedSet<T>　synchronizedSortedSet(SortedSet<T> s)
         指定されたソートセットを基にする同期 (スレッドセーフな) ソートセットを返します。
　　　-> SortedMapインタフェースに欲しい。
    -> default　<K,V> SortedMap<K,V>　synchronizedSortedMap()

static　<T> Collection<T>　unmodifiableCollection(Collection<? extends T> c)
         指定されたコレクションの変更不可能なビューを返します。
　　　-> Collectionインタフェースに欲しい。
    -> default　<T> Collection<T>　unmodifiableCollection(Collection<? extends T> c)

static　<T> List<T>　unmodifiableList(List<? extends T> list)
         指定されたリストの変更不可能なビューを返します。
　　　-> Listインタフェースに欲しい。
    -> default　<T> List<T>　unmodifiableList()

static　<K,V> Map<K,V>　unmodifiableMap(Map<? extends K,? extends V> m)
         指定されたマップの変更不可能なビューを返します。
　　　-> Mapインタフェースに欲しい。
    -> default　<K,V> Map<K,V>　unmodifiableMap()

static　<T> Set<T>　unmodifiableSet(Set<? extends T> s)
         指定されたセットの変更不可能なビューを返します。
　　　-> Setインタフェースに欲しい。
    -> default　<T> Set<T>　unmodifiableSet()

static　<K,V> SortedMap<K,V>　unmodifiableSortedMap(SortedMap<K,? extends V> m)
         指定されたソートマップの変更不可能なビューを返します。
　　　-> SortedMapインタフェースに欲しい。
    -> default　<K,V> SortedMap<K,V>　unmodifiableSortedMap()

static　<T> SortedSet<T>　unmodifiableSortedSet(SortedSet<T> s)
         指定されたソートセットの変更不可能なビューを返します。
　　　-> SortedSetインタフェースに欲しい。
    -> default　<T> SortedSet<T>　unmodifiableSortedSet()

*/

package jp.co.namihira.java8;


public class Main {

    public static void main(String[] args) {
        // prepare
        // - nothing

        // action
        // - nothing

        // check
        // - nothing
    }
}