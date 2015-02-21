/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * ユーザーの年齢を取得して、年齢に1加えて、Next year, you will be ... と表示する
 * nextYear.jsスクリプトを書きなさい。
 * 年齢は、コマンドラインで指定するか、環境変数AGEで指定することができます。
 * どちらも指定されていなければ、ユーザーに問い合わせるようにしなさい。
 */

var age

if ($ENV.AGE && $ENV.AGE != "") {
  try {
    age = java.lang.Integer.valueOf($ENV.AGE)
  } catch (e) {
    print('[WARNING] ENV.AGE ' + e)
  }
}

if ($ARG.length != 0) {
  try {
    age = java.lang.Integer.valueOf($ARG[0])
  } catch (e) {
    print('[WARNING] ' + e)
  }
}

while(!age || age < 0) {
  try {
    age = java.lang.Integer.valueOf(readLine('Type your age > '))
    if (age < 0)
       print('Please Type agein.')
  } catch (e) {
    print(e)
    print('Please Type agein.')
  }
}

var message = "Next year, you will be ${age + 1}."
print(message)

/*
 * - log
[ec2-user@xx-xx-xx-xx ~]$ jjs -scripting nextYear.js
Type your age > -20
Please Type agein.
Type your age > 20
Next year, you will be 21.

[ec2-user@xx-xx-xx-xx ~]$ jjs -scripting nextYear.js
Type your age > -29
Please Type agein.
Type your age > 29
Next year, you will be 30.

[ec2-user@xx-xx-xx-xx ~]$ jjs -scripting nextYear.js
Type your age > hoge
java.lang.NumberFormatException: For input string: "hoge"
Please Type agein.
Type your age > 29
Next year, you will be 30.

[ec2-user@xx-xx-xx-xx ~]$ jjs -scripting nextYear.js -- 29
Next year, you will be 30.

[ec2-user@xx-xx-xx-xx ~]$ jjs -scripting nextYear.js -- hoge
[WARNING] java.lang.NumberFormatException: For input string: "hoge"
Type your age > 50
Next year, you will be 51.

[ec2-user@xx-xx-xx-xx ~]$ export AGE=hoge

[ec2-user@xx-xx-xx-xx ~]$ jjs -scripting nextYear.js
[WARNING] ENV.AGE java.lang.NumberFormatException: For input string: "hoge"
Type your age > 60
Next year, you will be 61.

[ec2-user@xx-xx-xx-xx ~]$ export AGE=100

[ec2-user@xx-xx-xx-xx ~]$ jjs -scripting nextYear.js
Next year, you will be 101.
 */
