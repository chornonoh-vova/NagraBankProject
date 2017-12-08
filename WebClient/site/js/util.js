/*function to check login*/
function verifyLogin(login) {
	let regExp = /^[a-zA-Z]+([/._]?[a-zA-Z0-9]+)*$/;
	return regExp.test(login);
}

/*function to check pin code*/
function verifyPinCode(pin) {
	let regExp = /^[0-9]{4}$/;
	return regExp.test(pin);
}

/*function to check if date is in format: YYYY-MM-DD*/
function verifyDate(date) {
	let regExp = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;
	return regExp.test(date);
}

/*generate md5 hash from given input*/
function getMd5Hash(input) {
	return CryptoJS.MD5(input).toString();
}

/*function to set cookie with value and options like expires,path*/
function setCookie(name, value, options) {
  options = options || {};

  var expires = options.expires;

  if (typeof expires == "number" && expires) {
    var d = new Date();
    d.setTime(d.getTime() + expires * 1000);
    expires = options.expires = d;
  }
  if (expires && expires.toUTCString) {
    options.expires = expires.toUTCString();
  }

  value = encodeURIComponent(value);

  var updatedCookie = name + "=" + value;

  for (var propName in options) {
    updatedCookie += "; " + propName;
    var propValue = options[propName];
    if (propValue !== true) {
      updatedCookie += "=" + propValue;
    }
  }

  document.cookie = updatedCookie;
}

/*function to get cookie value with given name*/
function getCookie(name) {
  var matches = document.cookie.match(new RegExp(
    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
  return matches ? decodeURIComponent(matches[1]) : undefined;
}

/*deletes cookie with given name*/
function deleteCookie(name) {
  setCookie(name, "", {
    expires: -1
  })
}
 
/*function to test if value is valid float*/
function filterFloat(value) {
  if(/^(\-|\+)?([0-9]+(\.[0-9]+)?)$/.test(value)) {
    return Number(value);
  } else {
    return undefined;
  }
}