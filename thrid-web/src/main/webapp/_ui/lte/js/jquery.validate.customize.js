//anguo storefront
$(document).ready(function() {

	 // Mobile
	$.validator.addMethod("mobile", function(value, element) {
		 return this.optional( element ) || /^([1][34578][0-9]{9})$/.test( value );
	}, 'Please provide valid mobile number.');
	
	 // Money		
	$.validator.addMethod("money", function(value, element) {
		return this.optional( element ) || /^\d+(?:\.\d{0,2})?$/.test( value );
	}, 'Please provide valid format money.');
	
	
	$.validator.addMethod("moneyLength", function(value, element) {
		return this.optional( element ) || /^\d{1,10}(?:\.\d{0,2})?$/.test( value );
	}, 'Please provide valid length money number.');
	
	// Special Character	
	$.validator.addMethod("specialCharacterValidate", function(value, element) {
	 return this.optional( element ) || /^\w?(\w?[\u4e00-\u9fa5\w])+$/.test( value );
	}, 'Please enter a valid number or chinese character.');
	
	// Chinese Character	
	$.validator.addMethod("chineseCharacterValidate", function(value, element) {
	 return this.optional( element ) || /^[\x21-\x7e]+$/.test( value );
	}, 'Please do not enter chinese character.');
	
	//Word Character and Number
	$.validator.addMethod("alphanumeric", function(value, element) {
	 return this.optional( element ) || /[a-zA-Z0-9_\u4e00-\u9fa5]+/.test( value );
	}, 'Please enter letters, numbers or undercore.');
	
	$.validator.addMethod("valueNotEquals", function(value, element, arg){
		return arg != value;
	}, 'value not equals.');
	
	$.validator.addMethod("huifen", function(value, element) {
		if (value >= 0 && value <= 100)
			return true;
		else{
			return false;
		}
	}, 'Please enter correct huifen value');

	$.validator.addMethod("huifenLength", function(value, element) {
		return this.optional( element ) || /^\d{1,3}(?:\.\d{0,2})?$/.test( value );
	}, 'Please enter correct huifen length');
	
	$.validator.addMethod("positiveInteger", function(value, element) {
		return this.optional( element ) || /^\+?[1-9]\d*$/.test( value );
	}, 'Please provide a positive integer.');
		
	$.validator.addMethod("pwdComplex", function(pwd, element) {
		var number = false;
		var characters = false;
		for (i = 0; i < pwd.length; i++) {
			var t = pwd.charCodeAt(i);
			// only support certain characters, please refer to below weblink: http://unicode-table.com/cn/#control-character
			if (t < 33 || t > 126) {
				return false;
			}
			if (t >= 48 && t <= 57) { // 0-9
				number = true;
			} else { // other characters
				characters = true;
			}
		}
		
		if (number && characters) {
			return true;
		} else {
			return false;
		}
	}, 'Please enter a password with proper complex');
	
	$.validator.addMethod("stringExcludeSpecialChar", function(pwd, element,arg) {
		if (pwd.indexOf(arg) >= 0) {
			return false;
		}else {
			return true;
		}
	}, 'Please enter string which exclude the special char ');
	
	$.validator.addMethod("aftertoday", function(value, element) {
		var now = new Date();
		var date = new Date(Date.parse(value.replace(/-/g, "/")));
		if (date > now) {
			return true;
		} else {
			return false;
		}
	}, 'Please select a day after today.');
	
	$.validator.addMethod("multiple", function(value, element, param) {
		var target = param.split(",");
		var target1 = $( target[0] ).val();
		target1 = target1==""?0:parseInt(target1);
		var target2 = $( target[1] ).val();
		target2 = target2==""?0:parseInt(target2);
		var result = target2 % target1;
		if (result > 0) {
			return false;
		} else {
			return true;
		}
	}, 'Please enter the first multiple digital.');
});

$.extend($.validator.messages, {
    required: "必选字段",
    remote: "请修正该字段",
    email: "请输入正确格式的电子邮件",
    url: "请输入合法的网址",
    date: "请输入合法的日期",
    dateISO: "请输入合法的日期 (ISO).",
    number: "请输入合法的数字",
    digits: "只能输入整数",
    creditcard: "请输入合法的信用卡号",
    equalTo: "请再次输入相同的值",
    accept: "请输入拥有合法后缀名的字符串",
    maxlength: $.validator.format("请输入一个长度最多是 {0} 的字符串"),
    minlength: $.validator.format("请输入一个长度最少是 {0} 的字符串"),
    rangelength: $.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
    range: $.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
    max: $.validator.format("请输入一个最大为 {0} 的值"),
    min: $.validator.format("请输入一个最小为 {0} 的值")
});