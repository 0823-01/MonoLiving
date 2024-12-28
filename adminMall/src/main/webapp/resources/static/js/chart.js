/**
 * 
 */
 
   // 구글 차트 API 로드
	google.charts.load('current', {packages: ['corechart', 'bar', 'line']});
	
	// 차트 생성 함수 호출
	google.charts.setOnLoadCallback(drawLineChart);
	google.charts.setOnLoadCallback(drawVerticalBarChart);
	
	// 선그래프 그리기 함수
	function drawLineChart() {
	    var data = google.visualization.arrayToDataTable([
	        ['월', '거래량', '무료나눔'],
	        ['1월', 130, 30],
	        ['2월', 321, 50],
	        ['3월', 288, 63],
	        ['4월', 90, 142],
	        ['5월', 317, 96],
	        ['6월', 246, 25]
	    ]);
	
	    var options = {
	        title: '2023년 상반기 거래 현황',
	        hAxis: { title: '월' },
	        vAxis: { title: '건' },
	        legend: { position: 'bottom' }
	    };
	
	    var chart = new google.visualization.LineChart(document.getElementById('myLineChart'));
	    chart.draw(data, options);
	}
	
	// 세로 막대그래프 그리기 함수
	function drawVerticalBarChart() {
	    var data = google.visualization.arrayToDataTable([
	        ['카테고리', '판매수량'],
	        ['테이블', 231],
	        ['의자', 175],
	        ['커튼', 31],
	        ['조명', 379],
	        ['러그', 26]
	    ]);
	
	    var options = {
	        title: '카테고리별 판매 수량',
	        chartArea: { width: '50%' },
	        hAxis: { title: '카테고리' },
	        vAxis: { title: '판매수량', minValue: 0 }
	    };
	
	    var chart = new google.visualization.ColumnChart(document.getElementById('myVerticalBarChart'));
	    chart.draw(data, options);
	}