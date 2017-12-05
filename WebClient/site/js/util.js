function verifyLogin(login) {
	let regExp = /^[a-zA-Z]+([/._]?[a-zA-Z0-9]+)*$/;
	return regExp.test(login);
}

function verifyPinCode(pin) {
	let regExp = /^[0-9]{4}$/;
	return regExp.test(pin);
}

function verifyDate(date) {
	let regExp = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;
	return regExp.test(date);
}

function getMd5Hash(input) {
	return CryptoJS.MD5(input).toString();
}

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

function getCookie(name) {
  var matches = document.cookie.match(new RegExp(
    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
  ));
  return matches ? decodeURIComponent(matches[1]) : undefined;
}

function deleteCookie(name) {
  setCookie(name, "", {
    expires: -1
  })
}