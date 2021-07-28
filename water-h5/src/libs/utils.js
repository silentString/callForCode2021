export const uniq = (arr) => {
    var temp = [];
    for (var i = 0; i < arr.length; i++) {
        if (temp.indexOf(arr[i]) === -1) {
            temp.push(Number(arr[i]))
        }
    }
    return temp;
}

