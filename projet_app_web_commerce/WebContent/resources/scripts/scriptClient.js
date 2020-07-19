var ancienClient = document.getElementById('ancien-client');
var nouveauClient = document.getElementById('nouveau-client');
var formAncienClient = document.getElementById('form-ancien-client');
var formNewClient = document.getElementById('form-new-client');

ancienClient.addEventListener('click', function(e) {
	if (formAncienClient.style.display == 'none') {
		e.preventDefault();
		formAncienClient.style.display = 'block';
		formNewClient.style.display = 'none';
	}
});

nouveauClient.addEventListener('click', function(e) {
	if (formNewClient.style.display == 'none') {
		e.preventDefault();
		formNewClient.style.display = 'block';
		formAncienClient.style.display = 'none';
	}
});

