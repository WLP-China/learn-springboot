//form序列化为json
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//获取url后的参数值
function getUrlParam(key) {
    var href = window.location.href;
    var url = href.split("?");
    if (url.length <= 1) {
        return "";
    }
    var params = url[1].split("&");

    for (var i = 0; i < params.length; i++) {
        var param = params[i].split("=");
        if (key == param[0]) {
            return param[1];
        }
    }
}

/**
 * 判断字符串是否为空
 * @param str 传入的字符串
 * @returns {}
 */
function isNotEmpty(str) {
    // if (typeof (str) != "undefined" && str != null && str != "") {
    str = $.trim(str);
    if (str != null && str != "" && str.length > 0 && str != undefined) {
        return true;
    } else {
        return false;
    }
}

/**
 * 判断两个字符串子否相同
 * @param str1
 * @param str2
 * @returns {Boolean}
 */
function isEquals(str1, str2) {
    if (str1 == str2) {
        return true;
    } else {
        return false;
    }
}

/**
 * 忽略大小写判断字符串是否相同
 * @param str1
 * @param str2
 * @returns {Boolean}
 */
function isEqualsIgnorecase(str1, str2) {
    if (str1.toUpperCase() == str2.toUpperCase()) {
        return true;
    } else {
        return false;
    }
}

/**
 * 判断是否是数字
 * @param value
 * @returns {Boolean}
 */
function isNum(value) {
    if (value != null && value.length > 0 && isNaN(value) == false) {
        return true;
    } else {
        return false;
    }
}

/**
 * 判断是否是中文
 * @param str
 * @returns {Boolean}
 */
function isChine(str) {
    var reg = /^([u4E00-u9FA5]|[uFE30-uFFA0])*$/;
    if (reg.test(str)) {
        return false;
    }
    return true;
}

// 删除 回车
function removeEnter(str) {
    return str.replace(/\n/gi, "");
}