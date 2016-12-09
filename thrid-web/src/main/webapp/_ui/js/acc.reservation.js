ACC.reservation = {
	create : function() {
		$("#re-reservationPanel").panel("setTitle", "新建预约");
		$("#re-reservationForm").form("clear");
	},
	modify : function(value) {
		$("#re-reservationPanel").panel("setTitle", "修改预约");
		$("#re-reservationForm-reservationPK").val(value.pk);

		$.ajax({
			type : "get",
			url : ACC.config.contextPath + "/getReservation",
			data : {
				reservationPK : value.pk
			},
			success : function(data) {
				$("#re-reservationForm-cellphone").textbox("setValue",
						data.cellphone);
				$("#re-reservationForm-name").textbox("setValue", data.name);
				$("#re-reservationForm-reservationdate").datetimebox(
						"setValue", data.reservationDate);
				$("#re-reservationForm-store").combobox("setValue",
						data.store.code);
				$("#re-reservationForm-channel").val(data.channel);
				$("#re-reservationForm-channelText").textbox("setValue",
						data.channelText);
				$("#re-reservatioinForm-comment").textbox("setValue",
						data.comment);

				// $("#customerForm input[name='QQ']").val(data.qq);
				// $("#userForm-userGroupName").textbox("setValue",data.userGroup.name);$("#customerForm
				// input[name='email']").val(data.email);
			}
		})
	},
	save : function() {
		if ($("#re-reservationForm").form('validate')) {
			if ($("#re-reservationForm-reservationPK").val() == "")
				$.ajax({
					type : "post",
					url : ACC.config.contextPath + "/createReservation",
					data : $("#re-reservationForm").serialize(),
					success : function(data) {
						$.messager.alert("系统提示", "创建预约成功");
						ACC.reservation.create();
						ACC.reservationlist.load();
					}
				});
			else
				$.ajax({
					type : "post",
					url : ACC.config.contextPath + "/modifyReservation",
					data : $("#re-reservationForm").serialize(),
					success : function(data) {
						$.messager.alert("系统提示", "修改预约信息成功");
						ACC.reservationlist.load();
					}
				});
		}

	}
}

ACC.reservationlist = {
	init : function() {
		$("#rl-searchBtn").unbind();
		$("#rl-searchBtn").on("click", ACC.reservationlist.load);

		$("#rl-reservationListGrid").datagrid({
			// url:ACC.config.contextPath+"/getReservations",
			singleSelect : true,
			toolbar : '#rl-tb',
			iconCls : 'icon-save',
			pagination : true,
			rownumbers : true,
			idField : "pk",
			onDblClickRow : function(rowIndex, value) {
				ACC.reservation.modify(value);
			}
		});

		// var ctime = getCurrentDate();

		$("#rl-rStartDate").datetimebox({
		// value : ctime
		});

		$("#rl-rEndDate").datetimebox({
		// value : ctime
		});

		$("#rl-rStartDate").datetimebox().datetimebox('calendar').calendar({
			validator : function(date) {
				// var now = new Date();
				var enddate = $("#rl-rEndDate").datebox("getValue");
				return Date.parse(date) <= Date.parse(enddate);
			}
		});

		$("#rl-rEndDate").datetimebox().datetimebox('calendar').calendar({
			validator : function(date) {
				// var now = new Date();
				var begindate = $("#rl-rEndDate").datebox("getValue");
				return Date.parse(date) >= Date.parse(begindate);
			}
		});

	},
	load : function() {
		var searchForm = $("#rl-searchForm").form();
		if ($("#rl-reservationListGrid").datagrid("options").url == null)
			$("#rl-reservationListGrid").datagrid({
				url : ACC.config.contextPath + "/getReservations",
				queryParams : serializeObject(searchForm),
				pageNumber : 1
			});
		else
			$("#rl-reservationListGrid").datagrid("load",
					serializeObject(searchForm));
	},
	formatStore : function(val, row, index) {
		return row.store.name;
	}
}
