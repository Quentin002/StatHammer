const openModalBtn = document.querySelector('.modal_ouverture');
const modaleOverlay = document.querySelector('.modal_overlay');
const closeModalBtn = document.querySelector('.modal_retourButton')

//ouvrir la modal
openModalBtn.addEventListener('click', function () {
    modaleOverlay.classList.add('open');
});

closeModalBtn.addEventListener('click', function () {
    modaleOverlay.classList.remove('open');
});




