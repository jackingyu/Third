ACC.storedashboard = {
	query : function() {
		this.saveSearchCondition();
		$('#storeDashboardGrid').dataTable().fnDraw();
	},
	saveSearchCondition:function()
	{
		var orderDate = getDate4Range($("#orderDate").val());
		
		$("#savedSearchCondition input[name='startDate']").val(orderDate[0]);
		$("#savedSearchCondition input[name='endDate']").val(orderDate[1]);
		$("#savedSearchCondition input[name='customerSource']").val(
			$('#customerSources').val() != null ? $('#customerSources').val().toString() : ''		
		);
	},
	showPaymentDetails:function(obj)
	{
		$("#savedSearchCondition input[name='storeCode']").val($(obj).attr('storeCode'));
		$("#storeNameLabel").html($(obj).attr('storeName'));
		$("#paymentListPanel").modal("show");
		$('#paymentListGrid').dataTable().fnDraw();
		//ACC.storedashboard.queryPaymentByMethod();
	},
	getBarChartOptions : function() {
		var barChartOptions = {
			scaleBeginAtZero : true,
			scaleShowGridLines : true,
			scaleGridLineColor : 'rgba(0,0,0,.05)',
			scaleGridLineWidth : 1,
			scaleShowHorizontalLines : true,
			scaleShowVerticalLines : true,
			barShowStroke : true,
			barStrokeWidth : 2,
			barValueSpacing : 5,
			barDatasetSpacing : 1,
			legendTemplate : '<ul class="<%=name.toLowerCase()%>-legend"><% for (var i=0; i<datasets.length; i++){%><li><span style="background-color:<%=datasets[i].fillColor%>"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>',
			responsive : true,
			maintainAspectRatio : true
		};

		barChartOptions.datasetFill = false;
		return barChartOptions;
	},
	initPaymethodCanvas:function(data)
	{
		var pieChartCanvas = $('#paymentMethodChart').get(0).getContext('2d');
	    var pieChart       = new Chart(pieChartCanvas);
	    var PieData        = new Array();
	    var colors = ['#f56954','#00a65a','#f39c12'];
	    for (var i = 0; i < data.length; i++) {
			var item = {
				value : data[i][1],
				color : colors[i],
				highlight : colors[i],
				label : data[i][2]
			};
			PieData[i] = item;
		}
	   
	    var pieOptions     = {
	      segmentShowStroke    : true,
	      segmentStrokeColor   : '#fff',
	      segmentStrokeWidth   : 2,
	      percentageInnerCutout: 50, // This is 0 for Pie charts
	      animationSteps       : 100,
	      animationEasing      : 'easeOutBounce',
	      animateRotate        : true,
	      animateScale         : false,
	      responsive           : true,
	      maintainAspectRatio  : true,
	      legendTemplate       : '<ul class="<%=name.toLowerCase()%>-legend"><% for (var i=0; i<segments.length; i++){%><li><span style="background-color:<%=segments[i].fillColor%>"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>'
	    };
	    //Create pie or douhnut chart
	    // You can switch between pie and douhnut using the method below.
	    pieChart.Doughnut(PieData, pieOptions);
	},
	queryPaymentByMethod:function()
	{
		formData = getQuery('savedSearchCondition');
		var d = {};
		d.startDate = formData.startDate;
		d.endDate = formData.endDate;
		d.customerSources = formData.customerSources;
		d.storeCode = formData.storeCode;
		
		$.ajax({
			type : 'GET',
			url : ACC.config.contextPath + '/storedashboard/querypaymentpercentage',
			data : {startDate:d.startDate,endDate:d.endDate,customerSources:d.customerSources,storeCode:d.storeCode},
			error : function(request) {
				alert('Connection error');
			},
			success : function(data) {
				ACC.storedashboard.initPaymethodCanvas(data);
			}
		});
	},
	initOrderAmountCanvas : function(data) {
		var barChartCanvas = $('#orderAmountCanvas').get(0).getContext('2d');
		var barChart = new Chart(barChartCanvas);
		var storeArray = new Array();
		var paidAmountArray = new Array();
		var openAmountArray = new Array();
		var receiveableArray = new Array();

		for (var i = 0; i < data.length; i++) {
			storeArray[i] = data[i][5];
		}
		;
		for (var i = 0; i < data.length; i++) {
			paidAmountArray[i] = data[i][2];
		}
		;
		for (var i = 0; i < data.length; i++) {
			openAmountArray[i] = data[i][3];
		}
		;
		for (var i = 0; i < data.length; i++) {
			receiveableArray[i] = data[i][4];
		}
		;

		var barChartData = {
			labels : storeArray,
			datasets : [ {
				label : '已付金额',
				fillColor : '#00a65a',
				strokeColor : '#00a65a',
				pointColor : '#00a65a',
				pointStrokeColor : '#c1c7d1',
				pointHighlightFill : '#fff',
				pointHighlightStroke : 'rgba(220,220,220,1)',
				data : paidAmountArray
			}, {
				label : '未清金额',
				fillColor : '#ffcc00',
				strokeColor : '#ffcc00',
				pointColor : '#ffcc00',
				pointStrokeColor : 'rgba(60,141,188,1)',
				pointHighlightFill : '#fff',
				pointHighlightStroke : 'rgba(60,141,188,1)',
				data : openAmountArray
			} ]
		};

		barChart.Bar(barChartData, this.getBarChartOptions());

	},
	initOrderNumberCanvas : function(data) {
		var barChartCanvas = $('#orderNumberCanvas').get(0).getContext('2d');
		var barChart = new Chart(barChartCanvas);
		var storeArray = new Array();
		var orderNumberArray = new Array();

		for (var i = 0; i < data.length; i++) {
			storeArray[i] = data[i][5];
		}
		;
		for (var i = 0; i < data.length; i++) {
			orderNumberArray[i] = data[i][1];
		}
		;

		var barChartData = {
			labels : storeArray,
			datasets : [ {
				label : '订单数量',
				fillColor : '#00a65a',
				strokeColor : '#00a65a',
				pointColor : '#00a65a',
				pointStrokeColor : '#c1c7d1',
				pointHighlightFill : '#fff',
				pointHighlightStroke : 'rgba(220,220,220,1)',
				data : orderNumberArray
			} ]
		};

		barChart.Bar(barChartData, this.getBarChartOptions());
	},
	init : function() {
		$('#orderDate').daterangepicker({
			locale : datepicker_locale_zh,
			startDate : moment().subtract(1, 'months'),
			endDate : moment().add(2, 'years')
		});

		$("#paymentListPanel").on("shown.bs.modal",function(){
			ACC.storedashboard.queryPaymentByMethod();
		});
		
		$('.select2').val(''); // Select the option with a value of 'US'
		$('.select2').trigger('change');

		$('#storeDashboardGrid').DataTable(
				{
					'createdRow' : function(row, data, index) {
						$('td', row)
						.eq(0)
						.html(
								"<a  class='btn' storeName='"
								+ data[5]
								+ "' storeCode='"
								+ data[0]
								+ "' onclick='ACC.storedashboard.showPaymentDetails(this)'>"+data[5]+"</a>");
					},
					'bPaginate' : false,
					'ajax' : {
						'url' : ACC.config.contextPath
						+ '/storedashboard/query',
						'data' : function(d) {
							formData = getQuery('storeDashboardForm');
							var orderDate = getDate4Range($("#orderDate").val());
							d.startDate = orderDate[0];
							d.endDate = orderDate[1];

							d.storeCodes = $('#storeCodes').val() != null ? $(
							'#storeCodes').val().toString()
							: '';
							d.customerSources = $('#customerSources').val() != null ? $('#customerSources').val().toString() : '';
						}
					},
					'fnDrawCallback' : function(obj) {
						var result = new Array();
						for (var i = 0; i < obj.aoData.length; i++) {
							result[i] = obj.aoData[i]._aData;
						}
						ACC.storedashboard
						.initOrderAmountCanvas(result);
						ACC.storedashboard
						.initOrderNumberCanvas(result);
					}
				});
		
		$('#paymentListGrid').DataTable(
				{
					'createdRow' : function(row, data, index) {
						$('td', row).eq(4).html(	"<a  href='"+ACC.config.contextPath+"/order/modifyorderpage/"
								+data[4]+"' target='_blank'>"+data[4]+"</a>");
					},
					'ajax' : {
						'url' : ACC.config.contextPath
						+ '/storedashboard/querypaymentdetails',
						'data' : function(d) {
							formData = getQuery('savedSearchCondition');
							d.startDate = formData.startDate;
							d.endDate = formData.endDate;
							d.customerSources = formData.customerSources;
							d.storeCode = formData.storeCode;
						}
					},
					'fnDrawCallback' : function(obj) {
						//iDraw>1  means not initial
						//if(obj.iDraw > 1)
					
//						var result = new Array();
//						for (var i = 0; i < obj.aoData.length; i++) {
//							result[i] = obj.aoData[i]._aData;
//						}
//						ACC.storedashboard
//						.initOrderAmountCanvas(result);
//						ACC.storedashboard
//						.initOrderNumberCanvas(result);
					}
				});
	}
}

$(document).ready(function() {
	ACC.storedashboard.init();
});
