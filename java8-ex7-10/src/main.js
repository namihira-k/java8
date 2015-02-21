/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * 指定したファイルからデータを読み込み、パイチャートを表示するJavaFXプログラムをJavaScriptで書きなさい。
 * Javaでそのプログラムを開発するよりは、簡単ですか、難しいですか。その理由は何ですか？
 */
/*
 * A.
 * - Javaでそのプログラムを開発するよりは、簡単ですか、難しいですか。その理由は何ですか？
 * 少し難しい
 * [理由]
 * ロジック的には開発は基本的に同等の難易度であるが、
 * 型に寛容であるため、値への代入の際に型不一致な代入ミスが多々起こった。
 */

csvDatas = getCsvDatas('./data.csv')

datas = csvDatas.stream()
                .map(function(x) new javafx.scene.chart.PieChart.Data(x[0], x[1]))
                .collect(java.util.stream.Collectors.toList())
pieChartData =　javafx.collections.FXCollections.observableArrayList(datas)
chart = new javafx.scene.chart.PieChart(pieChartData)
chart.title = 'Population of the Continents'

group = new javafx.scene.Group(chart)
scene = new javafx.scene.Scene(group)

$STAGE.scene = scene
$STAGE.width = 500
$STAGE.height = 500

function getCsvDatas(filePath) {
	var br
	try {
	  br = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(new java.io.File(filePath))));
	  var csvData = new java.util.ArrayList
      for(var line = br.readLine(); line != null; line = br.readLine()) {
    	  csvData.add(line.split(','))
      }
	} catch (e) {
		print(e)
		exit(1)
	} finally {
		br.close()
	}
	return csvData
}
