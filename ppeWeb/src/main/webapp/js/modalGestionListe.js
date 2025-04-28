const openModalBtn = document.querySelectorAll('.modal_ouverture');
const modaleOverlay = document.querySelector('.modal_overlay');
const closeModalBtn = document.querySelector('.modal_retourButton')

//ouvrir la modal
openModalBtn.forEach((button) => {
  button.addEventListener('click', function () {
    modaleOverlay.classList.add('open');
    console.log(this.id); 
  });
});
	


closeModalBtn.addEventListener('click', function () {
    modaleOverlay.classList.remove('open');
});




