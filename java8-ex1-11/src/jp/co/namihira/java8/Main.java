/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * void f() メソッドを持つ、IとJの2つのインタフェースがあり、両方を実装しているクラスがあるとします。
 * Iインタフェースのfメソッドが抽象、デフォルト、staticのどれかであり、
 * Jインタフェースのfメソッドが抽象、デフォルト、staticのどれかである場合、
 * すべての組合せで何が起きるでしょうか？
 *
 * 同じように、スーパークラスSを拡張し、Iインタフェースを実装した場合、
 * スーパークラスもインタフェースもvoid f()メソッドを持っていたら、どうなるか調べなさい。
 */
/**
 * A.
 * 【Iインタフェースのfメソッド - Jインタフェースのfメソッド】
 * 抽象-抽象 -> 抽象メソッド実装のため、要オーバーライド
 * 抽象-デフォルト ->　名前衝突のため、要オーバーライド
 * 抽象-static -> 抽象メソッド実装のため、要オーバーライド
 * デフォルト-デフォルト ->　名前衝突のため、要オーバーライド
 * デフォルト-static -> オーバーライド不要
 * static-static -> オーバーライド不要
 *
 * 【スパークラスSのfメソッド - Iインタフェースのfメソッド】
 * static-抽象 -> 名前衝突。またインスタンスメソッドとstaticメソッドの競合によりクラス定義不可。
 *      (staticでオーバーライド->抽象メソッド定義不可。インスタンスメソッドでオーバーライド->インスタンスメソッドでstaticメソッドをオーバーライドは不可)
 * static-デフォルト -> 同上
 * static-static -> 名前衝突なし。オーバーライド不要。
 *      （staticクラスにてstaticメソッド追加可能（≠オーバライド））
 * インスタンス-抽象 -> 名前衝突なし。オーバーライド不要（f()を呼ぶとスーパークラスSのf()が呼ばれる）。オーバーライド可能（f()を呼ぶと子クラスのf()が呼ばれる）。
 * インスタンス-デフォルト -> 同上。
 * インスタンス-static -> 同上。
 */

package jp.co.namihira.java8;

public class Main {

    public static void main(String[] args) {
        // prepare
        // - nothing

        // action
        // - nothing

        // check
        // - See compile error and some System.out
    }

    static class IAbstractJAbstract implements IAbstract, JAbstract {
        @Override
        public void f() {
            System.out.println("called IAbstractJAbstract.f()");
        }
    }

    static class IAbstractJDefault implements IAbstract, JDefault {
        @Override
        public void f() {
            System.out.println("called IAbstractJDefault.f()");
        }
    }

    static class IAbstractJStatic implements IAbstract, JStatic {
        @Override
        public void f() {
            System.out.println("called IAbstractJStatic.f()");
        }
    }

    static class IDefaultJDefault implements IDefault, JDefault {
        @Override
        public void f() {
            System.out.println("called IDefaultJDefault.f()");
        }
    }

    static class IDefaultJStatic implements IDefault, JStatic {
//      @Override
        public void f() {
            System.out.println("called IDefaultJStatic.f()");
        }
    }

    static class IStaticJStatic implements IStatic, JStatic {
//        @Override
        public void f() {
            System.out.println("called IStaticJStatic.f()");
        }
    }

//   static class SStaticIAbstract extends SStatic implements IAbstract {
//        public void f() {
//            System.out.println("called SStaticIAbstract.f()");
//        }
//    }

//   static class SStaticIDefault extends SStatic implements IDefault {
//       public void f() {
//            System.out.println("called SStaticIDefault.f()");
//        }
//    }

    static class SStaticIStatic extends SStatic implements IStatic {
        static public void f() {
            System.out.println("called SStaticIStatic.f()");
        }
    }

    static class SInstanceIAbstract extends SInstance implements IAbstract {
//        public void f() {
//            System.out.println("called SInstanceIStatic.f()");
//        }
    }

    static class SInstanceIDefault extends SInstance implements IDefault {
//      public void f() {
//          System.out.println("called SInstanceIDefault.f()");
//      }
    }

    static class SInstanceIStatic extends SInstance implements IStatic {
//        public void f() {
//            System.out.println("called SInstanceIStatic.f()");
//        }
    }

}