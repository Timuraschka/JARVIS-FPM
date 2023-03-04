const form = document.querySelector('.login-form');

// add event listener to login form
form.addEventListener('submit', function(event) {
  // prevent form from submitting
  event.preventDefault();

  // get username and password from input fields
  const username = document.querySelector('#username').value;
  const password = document.querySelector('#password').value;

  // send login credentials to server
  const xhr = new XMLHttpRequest();
  xhr.open('POST', '/login/check', true);
  xhr.setRequestHeader('Content-Type', 'application/json');

  // handle server response
  xhr.onload = function() {
    // if login was successful, redirect to home page
    if (xhr.status === 200) {
      const response = JSON.parse(xhr.responseText);
      if (response.status === 'success') {
        // redirect to home page
        window.location.href = '/home';
      } else {
        // show error message
        alert(response.message);
      }
    } else {
      // show error message
      alert('An error occurred. Please try again.');
    }
  };

  // handle network error
  xhr.onerror = function() {
    // show error message
    alert('An error occurred. Please try again.');
  };

  // send credentials to server
  xhr.send(JSON.stringify({username: username, password: password}));
});
