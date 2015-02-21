/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * 練習問題6の回答は、Unixのパイプほどは優れていません。
 * なぜなら、2つ目のコマンドは、最初のコマンドが完了してからでないと実行されないからです。
 * processBuilderクラスを使用して、その問題を解決しなさい。
 */

function pipeAsync(){
  if (arguments.length == 0)
    exit(1)

  if (arguments.length == 1)
    return `${arguments[0]}`

  var command = arguments[0].split(' ')
  var builder = new java.lang.ProcessBuilder(command)
  var process = builder.start()

  for (var i = 1; i < arguments.length; i++) {
    var command2 = arguments[i].split(' ')
    var builder2 = new java.lang.ProcessBuilder(command2)
    var process2 = builder2.start()

    process.waitFor()

    var br
    var bw
    try {
      var br = new java.io.BufferedReader(new java.io.InputStreamReader(process.getInputStream()))
      var bw = new java.io.BufferedWriter(new java.io.OutputStreamWriter(process2.getOutputStream()))
      for(var line = br.readLine(); line != null; line = br.readLine()) {
        bw.write(line)
        bw.newLine()
      }
      process.destroy()
      process = process2
    } catch (e) {
      print(e)
      exit(1)
    } finally {
      try {
        br.close()
        bw.close()
      } catch (e) {
         print(e)
         exit(1)
      }
    }
  }

  process.waitFor()

  var result = getResult(process);
  process.destroy()

  return result
}

function getResult(process){
  var br
  var sb = new java.lang.StringBuilder()
  try {
    br = new java.io.BufferedReader(new java.io.InputStreamReader(process.getInputStream()))
    for(var line = br.readLine(); line != null; line = br.readLine()) {
      sb.append(line)
      sb.append('\n')
    }
  } catch (e) {
    print(e)
    exit(1)
  } finally {
    try {
      br.close()
    } catch (e) {
      print(e)
      exit(1)
    }
  }
  return sb.toString()
}


print('*** find . ***')
var result1 = pipeAsync('find .')
print(result1)

print('*** find . , grep -v js ***')
var result2 = pipeAsync('find .', 'grep -v js')
print(result2)

print('*** find ., grep -v js, sort ***')
var result3 = pipeAsync('find .', 'grep -v js', 'sort')
print(result3)

print('*** nothing ***')
var result0 = pipeAsync()
print(result0)


/* - log
[ec2-user@-xx-xx-xx-xx ~]$ jjs -scripting common-utils.js
*** find . ***
.
./.bash_logout
./townbook.war
./.pki
./.pki/nssdb
./.bash_profile
./.ssh
./.ssh/authorized_keys
./tmp
./tmp/java
./tmp/java/jp
./tmp/java/jp/namihira
./tmp/java/jp/namihira/util
./tmp/java/jp/namihira/util/SortClass.java
./tmp/java/jp/namihira/util/SortLambda.class
./tmp/java/jp/namihira/util/SortLambda.java
./tmp/java/cfr_0_90.jar
./twitter_bootstrap
./twitter_bootstrap/fonts
./twitter_bootstrap/fonts/glyphicons-halflings-regular.woff
./twitter_bootstrap/fonts/glyphicons-halflings-regular.eot
./twitter_bootstrap/fonts/glyphicons-halflings-regular.ttf
./twitter_bootstrap/fonts/glyphicons-halflings-regular.svg
./twitter_bootstrap/css
./twitter_bootstrap/css/bootstrap.css
./twitter_bootstrap/css/bootstrap-theme.min.css
./twitter_bootstrap/css/bootstrap.min.css
./twitter_bootstrap/css/bootstrap-theme.css
./twitter_bootstrap/js
./twitter_bootstrap/js/bootstrap.min.js
./twitter_bootstrap/js/bootstrap.js
./twitter_bootstrap/index.html
./.lesshst
./.viminfo
./system-utils.js
./.bashrc
./tomcat.sh
./common-utils.js_back
./.bash_history
./common-utils.js
./.mysql_history

*** find . , grep -v js ***
.
./.bash_logout
./townbook.war
./.pki
./.pki/nssdb
./.bash_profile
./.ssh
./.ssh/authorized_keys
./tmp
./tmp/java
./tmp/java/jp
./tmp/java/jp/namihira
./tmp/java/jp/namihira/util
./tmp/java/jp/namihira/util/SortClass.java
./tmp/java/jp/namihira/util/SortLambda.class
./tmp/java/jp/namihira/util/SortLambda.java
./tmp/java/cfr_0_90.jar
./twitter_bootstrap
./twitter_bootstrap/fonts
./twitter_bootstrap/fonts/glyphicons-halflings-regular.woff
./twitter_bootstrap/fonts/glyphicons-halflings-regular.eot
./twitter_bootstrap/fonts/glyphicons-halflings-regular.ttf
./twitter_bootstrap/fonts/glyphicons-halflings-regular.svg
./twitter_bootstrap/css
./twitter_bootstrap/css/bootstrap.css
./twitter_bootstrap/css/bootstrap-theme.min.css
./twitter_bootstrap/css/bootstrap.min.css
./twitter_bootstrap/css/bootstrap-theme.css
./twitter_bootstrap/index.html
./.lesshst
./.viminfo
./.bashrc
./tomcat.sh
./.bash_history
./.mysql_history

*** find ., grep -v js, sort ***
.
./.bash_history
./.bash_logout
./.bash_profile
./.bashrc
./.lesshst
./.mysql_history
./.pki
./.pki/nssdb
./.ssh
./.ssh/authorized_keys
./tmp
./tmp/java
./tmp/java/cfr_0_90.jar
./tmp/java/jp
./tmp/java/jp/namihira
./tmp/java/jp/namihira/util
./tmp/java/jp/namihira/util/SortClass.java
./tmp/java/jp/namihira/util/SortLambda.class
./tmp/java/jp/namihira/util/SortLambda.java
./tomcat.sh
./townbook.war
./twitter_bootstrap
./twitter_bootstrap/css
./twitter_bootstrap/css/bootstrap.css
./twitter_bootstrap/css/bootstrap.min.css
./twitter_bootstrap/css/bootstrap-theme.css
./twitter_bootstrap/css/bootstrap-theme.min.css
./twitter_bootstrap/fonts
./twitter_bootstrap/fonts/glyphicons-halflings-regular.eot
./twitter_bootstrap/fonts/glyphicons-halflings-regular.svg
./twitter_bootstrap/fonts/glyphicons-halflings-regular.ttf
./twitter_bootstrap/fonts/glyphicons-halflings-regular.woff
./twitter_bootstrap/index.html
./.viminfo

*** nothing ***
*/
