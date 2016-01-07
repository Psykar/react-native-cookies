## react-native-cookies

Cookie manager for react native.

### Installation

1. `npm install react-native-cookies`
2. In the XCode's "Project navigator", right click on project name folder ➜ `Add Files to <...>`
 - Ensure `Copy items if needed` and `Create groups` are checked
3. Go to `node_modules` ➜ `react-native-cookies` ➜ add `RNCookieManagerIOS` folder
4. Compile and have some cookies

### Usage

```javascript
var CookieManager = require('react-native-cookies');

// set a cookie (IOS ONLY)
CookieManager.set({
  name: 'myCookie',
  value: 'myValue',
  domain: 'some domain',
  origin: 'some origin',
  path: '/',
  version: '1',
  expiration: '2015-05-30T12:30:00.00-05:00'
}, (err, res) => {
  console.log('cookie set!');
  console.log(err);
  console.log(res);
});

// list cookies (IOS ONLY)
CookieManager.getAll((err, res) => {
  console.log('cookies!');
  console.log(err);
  console.log(res);
});

// clear cookies
CookieManager.clearAll((err, res) => {
  console.log('cookies cleared!');
  console.log(err);
  console.log(res);
});

// Get cookies as a request header string
CookieManager.getCookieHeader('http://example.com', (res) => {
  console.log('Got cookies for url', res);
  // Outputs 'user_session=abcdefg; path=/;'
})

// Set cookies from a response header (untested)
CookieManager.setFromHeader('http://example.com', 'user_session=abcdefg; path=/; expires=Thu, 1 Jan 2030 00:00:00 -0000; secure; HttpOnly', (res) => {
  console.log("Set cookie", res);
})


```

### Roadmap

- Proper `getAll` dictionary by domain
- Proper error handling
- Anything else?

PR's welcome!
