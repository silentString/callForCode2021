import Vue from 'vue'
export function cutString(str, len) {
    let strlen = 0;
    let s = "";
    for (let i = 0; i < str.length; i++) {
        if (str.charCodeAt(i) > 255) {
            strlen += 2;
        } else {
            strlen++;
        }
        s += str.charAt(i);
        if (strlen >= len) {
            return s;
        }
    }
    return s;
}

export function setString(str, len) {
    let strlen = 0;
    let s = "";
    try {
        for (let i = 0; i < str.length; i++) {
            if (str.charCodeAt(i) > 255) {
                strlen += 2;
            } else {
                strlen++;
            }

            if (strlen <= len) {
                s += str.charAt(i);
            }
        }
        if (strlen >= len) {
            return s + "...";
        }
    } catch (error) {
        console.log(error)
    }

    return s;
}
//0已提交1已撤回2审批通过3审批拒绝
export function changeStatus(state) {
    let status = ''
    switch (state) {
        case 0:
            status = 'Submitted';
            break;
        case 3:
            status = 'Failed';
            break;
        case 2:
            status = 'Passed';
            break;
        case 1:
            status = 'Revoked';
            break;
        case '0':
            status = 'Submitted';
            break;
        case '3':
            status = 'Failed';
            break;
        case '2':
            status = 'Passed';
            break;
        case '1':
            status = 'Revoked';
            break;
    }
    return status
}
export function changeClassName(state) {
    let status = ''
    switch (state) {
        case 0:
            status = 'sub';
            break;
        case 3:
            status = 'fail';
            break;
        case 2:
            status = 'pass';
            break;
        case 1:
            status = 'canceled';
            break;
        case '0':
            status = 'sub';
            break;
        case '3':
            status = 'fail';
            break;
        case '2':
            status = 'pass';
            break;
        case '1':
            status = 'canceled';
            break;
    }
    return status
}
//千分化
export function toThousands(num) {
    let result = "",
        counter = 0;
    num = (num || 0).toString();
    for (let i = num.length - 1; i >= 0; i--) {
        counter++;
        result = num.charAt(i) + result;
        if (!(counter % 3) && i !== 0) {
            result = ',' + result
        }
    }
    return result
}


export function StringHidden(str, frontLen, endLen) {
    //str：要进行隐藏的变量  frontLen: 前面需要保留几位    endLen: 后面需要保留几位
    if (str && str.length > 0) {
        var len = str.length - frontLen - endLen;
        var xing = "";
        for (var i = 0; i < len; i++) {
            xing += "*";
        }
        return (
            str.substring(0, frontLen) + xing + str.substring(str.length - endLen)
        );
    }
}

export function ChangeTime(date) {
    if (date && date.indexOf('T') !== -1) {
        var arr = date.split("T");
        var d = arr[0];
        var darr = d.split('-');
        var t = arr[1];
        var tarr = t.split('.000');
        var marr = tarr[0].split(':');
        var mins=''
        if(parseInt(marr[1])<10 || parseInt(marr[1])===0){
             mins= '0'+ parseInt(marr[1])   
        }else{
             mins=  parseInt(marr[1]) 
        }
        var dd = parseInt(darr[0]) + "/" + parseInt(darr[1]) + "/" + parseInt(darr[2]) + " " + parseInt(Number(marr[0]) + 8) + ":" + mins;
        return dd;
    } else {
        return date
    }
}

export function number_format(number, decimals, dec_point, thousands_sep) {
    /*
     * 参数说明：
     * number：要格式化的数字
     * decimals：保留几位小数
     * dec_point：小数点符号
     * thousands_sep：千分位符号
     * */
    number = (number + '').replace(/[^0-9+-Ee.]/g, '');
    var n = !isFinite(+number) ? 0 : +number,
        prec = !isFinite(+decimals) ? 2 : Math.abs(decimals),
        sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
        dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
        s = '',
        toFixedFix = function (n, prec) {
            var k = Math.pow(10, prec);
            return '' + Math.ceil(n * k) / k;
        };

    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
    var re = /(-?\d+)(\d{3})/;
    while (re.test(s[0])) {
        s[0] = s[0].replace(re, "$1" + sep + "$2");
    }

    if ((s[1] || '').length < prec) {
        s[1] = s[1] || '';
        s[1] += new Array(prec - s[1].length + 1).join('0');
    }
    return s.join(dec);
}

const filters = {
    setString: setString,
    cutString: cutString,
    toThousands: toThousands,
    StringHidden: StringHidden,
    ChangeTime: ChangeTime,
    number_format: number_format,
    changeClassName: changeClassName,
    changeStatus: changeStatus

};
Object.keys(filters).forEach(key => {
    Vue.filter(key, filters[key])
});

export default filters