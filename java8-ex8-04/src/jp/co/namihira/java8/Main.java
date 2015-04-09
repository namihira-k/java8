/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * Math.nextDown(x)メソッドは、何らかのランダムな処理がxに正確に一致した場合に、
 * xよりも次に小さな浮動小数点数を返します。
 * これにより、返された数がxより小さいことが保証されます。
 * これは、本当に保証できるのでしょうか。
 * double r = 1 - generator.nextDouble()を考えてみてください。
 * このgeneratorは、java.util.Random のインスタンスです。
 * rが、1になることはあるでしょうか。すなわち、generator.nextDouble()が0を生成できるでしょうか。
 * ドキュメントによれば、それは0を含み、1未満の値を生成できるとなっています。
 * しかし、2^53個の浮動小数点数が存在することを考慮すると、0を得ることはあるのでしょうか。
 * 実際、0を得ます。乱数生成器は、次のシードをnext(s)=s*m + a%Nとして計算します。
 * ここで、m=25214903917、a=11、N=2^48です。mモジュロN の逆はv=246154705703781です。
 * そして、結果として、シードの前の値をprev(s)=(s-a)*v%Nとして計算することができます。
 * doubleを生成するために2つの乱数が生成されて、毎回、トップの26ビットと27ビットが使用されます。
 * sが0の場合、next(s)は11であり、それが私達が得たいものです。すなわち、2つの連続する数字の上位ビットが0です。
 * ここで、逆にやりなおして、s=prev(prev(prev(0)))から始めましょう。
 * Random のコンストラクタはs=(initailSeed^m)を設定するので、s=prev(prev(prev(0)))^m=164311266871034
 * を提供すると、nextDouble の2回の呼び出し後に0 となります。しかし、それはあまりにも明白です。
 * 100万個の前の値を、もちろん、ストリームを使用して生成し、最小のシードを見つけなさい。
 * ヒント:nextDoubleを376050回呼び出した後に0を得ます。
 */

package jp.co.namihira.java8;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class Main {

    private final static long m = 25214903917L;

    private final static int COUNT_MEASURE = 1_000_000;
    private final static int COUNT_NEXT_DOUBLE = 376049;

    /**
     * FIXME
     *
     * min seed : 258079733
     * 0.6377114653006212
     * 0.818933505835191
     * 0.6247993079588533
     *
     */
    public static void main(String[] args) {
        // setup
        final AtomicLong min = new AtomicLong(Long.MAX_VALUE);
        final AtomicLong seed = new AtomicLong(0L);
        final Stream<Long> seeds = Stream.generate(() -> {
            final long v = MathUtils.prev(seed.get());
            min.updateAndGet(x -> Math.min(x, v));
            seed.set(v);
            return v;
        }).limit(COUNT_MEASURE);

        // action
        seeds.sequential().forEachOrdered(s -> {return;});

        // check
        System.out.println("min seed : " + min.get());
        final Random generator = new Random(min.get() ^ m);
        final Stream<Double> randoms = Stream.generate(() -> generator.nextDouble()).limit(COUNT_NEXT_DOUBLE);

        randoms.sequential().forEachOrdered(r -> {return;});
        System.out.println(generator.nextDouble());
        System.out.println(generator.nextDouble());
        System.out.println(generator.nextDouble());
    }

}
