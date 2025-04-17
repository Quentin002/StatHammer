document.addEventListener('DOMContentLoaded', () => {
    const popup = document.getElementById('popup');
    const popupImg = document.getElementById('popupImg');
    const popupClose = document.getElementById('popupClose');

    document.querySelectorAll('.accueil-image').forEach(img => {
        img.addEventListener('click', () => {
            popupImg.src = img.src;
            popup.classList.add('active');
        });
    });

    popupClose.addEventListener('click', () => {
        popup.classList.remove('active');
    });

    popup.addEventListener('click', (e) => {
        if (e.target === popup) {
            popup.classList.remove('active');
        }
    });
});
/**
 * 
 */