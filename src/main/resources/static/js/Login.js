
const form = document.querySelector('.login-form');
form.addEventListener('submit', function(event) {
  event.preventDefault();
  
  const username = document.querySelector('#username').value;
  const password = document.querySelector('#password').value;

  const xhr = new XMLHttpRequest();
  xhr.open('POST', '/login/check', true);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.onload = function() {
    if (xhr.status === 200) {
      const response = JSON.parse(xhr.responseText);
      if (response.status === 'success') {
        // Weiterleitung zur erfolgreichen Anmeldung
        window.location.href = '/home';
      } else {
        // Anzeige einer Fehlermeldung
        alert(response.message);
      }
    } else {
      alert('Ein Fehler ist aufgetreten. Bitte versuchen Sie es erneut.');
    }
  };
  xhr.onerror = function() {
    alert('Ein Fehler ist aufgetreten. Bitte versuchen Sie es erneut.');
  };
  xhr.send(JSON.stringify({username: username, password: password}));
});

