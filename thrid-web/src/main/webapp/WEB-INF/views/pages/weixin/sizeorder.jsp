<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>量身单</title>
<link rel="stylesheet" href="../ext-3.4.0/resources/css/ext-all.css"
	type="text/css" />
<link rel="stylesheet" href="../ext-3.4.0/resources/css/xtheme-gray.css"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/ext-patch.css" />
<link rel="stylesheet" type="text/css" href="../css/icons.css" />
<script src="../ext-3.4.0/adapter/ext/ext-base.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../ext-3.4.0/ext-all.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../ext-3.4.0/src/locale/ext-lang-zh_CN.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../ext-3.4.0/examples/ux/SearchField.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../js/sizeOrder.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/sizeOrderDetail.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript">
	var id =<%=request.getParameter("id")%>;
	Ext.onReady(function() {
		Ext.override(Ext.form.ComboBox, {
			editable : false
		});
		Ext.Ajax.request({
			url : '../sales/sizeOrder/get',
			params : {
				sid : id
			},
			success : function(o) {
				var returns = Ext.decode(o.responseText);
				var array = new Array();
				var type = returns.type;
				var initParam = {
					id : id
				};
				var form;
				switch (type) {
					case 'Shirt':
						form = getShirtForm(initParam);
						break;
					case 'Suit':
						form = getSuitForm(initParam);
						break;
					case 'Trousers':
						form = getTrousersForm(initParam);
						break;
					case 'Vest':
						form = getTrousersForm(initParam);
						break;
				}
				valuesToArray(array, returns, 'sizeOrder' + type + '.');
				form.getForm().setValues(array);
				form.render('sizeorder');
			},
			failure : function() {
				ajaxError();
			}
		});
	});
	function valuesToArray(array, values, prefix) {
		for (id in values) {
			if (Ext.isObject(values[id])) {
				valuesToArray(array, values[id], prefix + id + ".");
			} else {
				array.push({
					id : prefix + id,
					value : values[id]
				})
			}
		}
	}
	var storeDs = new Ext.data.Store({
		autoLoad : true,
		url : '../masterdata/store/list',
		reader : new Ext.data.JsonReader({
			"idProperty" : "id"
		}, [ {
			name : 'id',
			mapping : 'id'
		}, {
			name : 'storeName',
			mapping : 'storeName'
		} ])
	});

	var storeDs2 = new Ext.data.Store({
		autoLoad : true,
		url : '../masterdata/store/listAll',
		reader : new Ext.data.JsonReader({
			"idProperty" : "id"
		}, [ {
			name : 'id',
			mapping : 'id'
		}, {
			name : 'storeName',
			mapping : 'storeName'
		} ])
	});

	var coCompanyDs = new Ext.data.Store({
		autoLoad : true,
		url : '../masterdata/coCompany/list',
		reader : new Ext.data.JsonReader({}, [ {
			name : 'id',
			mapping : 'id'
		}, {
			name : 'coCompanyName',
			mapping : 'coCompanyName'
		} ])
	});

	var orderTypeDs = new Ext.data.Store({
		autoLoad : true,
		url : '../sales/orderType/list',
		reader : new Ext.data.JsonReader({}, [ {
			name : 'id',
			mapping : 'id'
		}, {
			name : 'name',
			mapping : 'name'
		} ])
	});

	var supplySourceDs = new Ext.data.Store({
		autoLoad : true,
		url : '../masterdata/supplySource/list',
		reader : new Ext.data.JsonReader({}, [ {
			name : 'id',
			mapping : 'id'
		}, {
			name : 'supplySourceName',
			mapping : 'supplySourceName'
		} ])
	});

	var exhibitionDs = new Ext.data.Store({
		autoLoad : true,
		url : '../masterdata/exhibition/list',
		reader : new Ext.data.JsonReader({}, [ {
			name : 'id',
			mapping : 'id'
		}, {
			name : 'exhibitionName',
			mapping : 'exhibitionName'
		} ])
	});

	var paymentDs = new Ext.data.ArrayStore({
		fields : [ 'value', 'text' ],
		data : [ [ '1', '现金' ], [ '2', '刷卡' ] ]
	});

	var menuDs = new Ext.data.ArrayStore({
		fields : [ 'value', 'text' ],
		data : [ [ '../xml/boss.xml', '管理层' ], [ '../xml/design.xml', '设计师' ], [ '../xml/sales.xml', '销售' ], [ '../xml/authority.xml', '管理员' ],
				[ '../xml/manager.xml', '经理' ], [ '../xml/worker.xml', '工人' ] ]
	});

	var authorityDs = new Ext.data.ArrayStore({
		fields : [ 'value', 'text' ],
		data : [ [ 'x', '允许' ], [ '', '禁止' ] ]
	});

	var roleDs = new Ext.data.Store({
		autoLoad : true,
		url : '../xml/role.xml',
		reader : new Ext.data.XmlReader({
			record : 'role',
			idProperty : 'name'
		}, [ {
			name : 'name',
			mapping : 'name'
		}, {
			name : 'value',
			mapping : 'value'
		} ])
	});

	var designerDs = new Ext.data.Store({
		autoLoad : true,
		url : '../masterdata/user/getDesigners',
		reader : new Ext.data.JsonReader({}, [ {
			name : 'id',
			mapping : 'userId'
		}, {
			name : 'name',
			mapping : 'name'
		} ])
	});

	var approvalDs = new Ext.data.ArrayStore({
		fields : [ 'value', 'text' ],
		data : [ [ '-1', '全部' ], [ '1', '审批通过' ], [ '0', '未审批' ], [ '99', '关闭' ] ]
	});
</script>
</head>
<body>
<div id="sizeorder"></div>
</body>
</html>