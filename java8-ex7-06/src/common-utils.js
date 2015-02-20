/*
 * Q.
 * 一連のシェルコマンドを受け取り、1つのコマンドの出力を次のコマンドの入力として接続し、
 * 最後の出力を返すJavaScriptのpipe関数を書きなさい。
 * 例えば、pipe('find .', 'grep -v class', 'sort')と呼び出されます。
 * 単純に$EXECを繰り返し呼び出しなさい。
 */

function pipe(){
  if (arguments.length == 0)
    exit(1)

  var result = `${arguments[0]}`
  if (arguments.length == 1)
    return result

  for (var i = 1; i < arguments.length; i++)
    result = $EXEC(arguments[i], result)

  return result
}

print('*** find . ***')
var result1 = pipe('find .')
print(result1)

print('*** find . , grep -v common-utils.js ***')
var result2 = pipe('find .', 'grep -v common-utils.js')
print(result2)

print('*** find ., grep -v common-utils.js, sort ***')
var result3 = pipe('find .', 'grep -v common-utils.js', 'sort')
print(result3)

print('*** nothing ***')
var result0 = pipe()
print(result0)
