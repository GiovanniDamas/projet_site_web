var list = document.getElementById('button-list');
var grid = document.getElementById('button-grid');
var listproduit = document.getElementById('listproduit');
var gridproduit = document.getElementById('gridproduit');

list.addEventListener('click', function(e) {
	if (listproduit.style.display == 'none') {
		e.preventDefault();
		listproduit.style.display = 'block';
		gridproduit.style.display = 'none';
	}
});

grid.addEventListener('click', function(e) {
	if (gridproduit.style.display == 'none') {
		e.preventDefault();
		gridproduit.style.display = 'block';
		listproduit.style.display = 'none';
	}
});