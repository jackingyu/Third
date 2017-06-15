ACC.common = {
	refreshScreenReaderBuffer:function() {
		$('#accesibility_refreshScreenReaderBufferField').attr('value', new Date().getTime());
	}
}

 this.$('#storeManagementListGrid').dataTable({
    	    	'oLanguage': {
//    	    		"sSearch": "搜索条件",
    	    		'sZeroRecords': '抱歉， 没有找到',
    	    		'sInfoEmpty': '无数据',
    	    		'sEmptyTable': '抱歉,没有找到符合要求的数据',
    	    		'sInfoFiltered':'',
    	    		'sProcessing': "正在加载数据...",
    	    		'sInfo': "共 _TOTAL_ 条",
    	    		'sLengthMenu': '每页显示 _MENU_ 条',
    	    		'oPaginate': {
    	    			"sFirst": "首页",
                        "sPrevious": "上一页",
                        "sNext": "下一页",
                        "sLast": "尾页"
        	    	}
    	    	},
    	    	'aaSorting': [[ 7, "desc" ]],
    	    	'aoColumnDefs': [ { "bSortable": false, "aTargets": [ 0,3,4,5,11 ] } , { sDefaultContent : '', aTargets : [ '_all' ] }],
    	    	'bPaginate': true,
    	    	'sPaginationType': "full_numbers",
    	    	'bLengthChange': true,
    	    	'bFilter': false,
    	    	'bProcessing': true,//开启读取服务器数据时显示正在加载中……特别是大数据量的时候，开启此功能比较好
    	    	'bServerSide': true,//开启服务器模式，你对datatables的每个操作 每页显示多少条记录、下一页、上一页、排序（表头）、搜索，这些都会传给服务器相应的值
    	    	'aLengthMenu': [[20, 50, 100,100000], [20, 50, 100,"全部"]],
    	    	'sDom' : 'frtipl',
    	    	"bStateSave" : true,
    	    	"createdRow": function ( row, data, index ) {
					if(data[6]!=null){
						$('td', row).eq(6).html(data[6].replace(" ","<br/>"));
					}
					if(data[7]!=null){
						$('td', row).eq(7).html(data[7].replace(" ","<br/>"));
					}
					if(data[8]!=null){
						$('td', row).eq(8).html(data[8].replace(" ","<br/>"));
					}
 					$('td', row).eq(11).html("<a href='#stores/storeListDetail?storeCode="+data[11]+"'>编辑</a>");
     			},
    			"ajax": {
    				"url":"/admin/storeManagement/getAllStoreList",
    				"data": function (d) {
    					d.approveStatus = $('#approveStatus option:selected').val();
    					d.userType = $('#userType option:selected').val();
    					d.queryCondition = $('#queryCondition option:selected').val();
    					d.queryValue = $('#queryValue').val();
						d.startCreateDate = $('#startCreateDate').val();
						d.endCreateDate = $('#endCreateDate').val();
						d.startUpdateDate = $('#startUpdateDate').val();
						d.endUpdateDate = $('#endUpdateDate').val();
						d.startRegDate = $('#startRegDate').val();
						d.endRegDate = $('#endRegDate').val();
						d.createMode = $('#selCreateType').val();
    				}
    			},
    			"fnDrawCallback": function( ) {
     				this.dataTable().enableedit({ 
	    				'enableSelector': true
			 			});
     				$('#pageLengthError').val($('#storeManagementListGrid').DataTable().page.info().pages);
    			}
    	    	});