const openModalBtn = document.querySelectorAll('.modal_ouverture');
const modaleOverlay = document.querySelector('.modal_overlay');
const closeModalBtn = document.querySelector('.modal_retourButton');
const supprBtn = document.querySelector('.modal_supprButton');
//ouvrir la modal
openModalBtn.forEach((button) => {
  button.addEventListener('click', function () {
    modaleOverlay.classList.add('open');
	supprBtn.setAttribute('value', this.id );
    console.log(this.id); 
  });
});

closeModalBtn.addEventListener('click', function () {
    modaleOverlay.classList.remove('open');
	supprBtn.removeAttribute('id');
});




