/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * なぜ、次の呼び出しができないのでしょうか？
 *   UnaryOperator op = Color::brighter;
 *   Image finalImage = transform(image, op.compose(Color::grayscale));
 * UnaryOperator<T>のcomposeメソッドの戻り値型を注意深く調べなさい。
 * なぜ、transformメソッドに対して、適切ではないのでしょうか？
 * 関数合成に関しては、ストラクチャル型（structual type）とノミナル型（nominal type）の
 * どちらがより役立つでしょうか。
 */
/**
 * A.
 *　- なぜ、次の呼び出しができないのでしょうか？
 * - なぜ、transformメソッドに対して、適切ではないのでしょうか？
 * UnaryOperatorのもつcomposeメソッドは、関数型インタフェースFunctionから継承おり、
 * その戻り値型はFunction<V,R>をなっている。
 * 一方でtransformメソッドの第2引数はUnaryOperator<T>を要求しており、
 * そこにFunction型を代入することができないため、上記の呼び出しが成立しない。
 *
 * - どちらがより役立つでしょうか。
 *　関数合成においての関心事は、その入力値（とその型）と戻り値（とその型）であり、
 * 整合のとれた型保証のようなノミナル型よりも、 構造（メソッドのシンタックスなど）での
 * 整合性を重視できるストラクチャル型のほうが関数を柔軟に合成できる。
 * ※Javaは型利用の一貫性を重視したため、ノミナル型視点での関数合成を行う必要がある。
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