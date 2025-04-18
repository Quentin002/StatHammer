document.getElementById('imageUpload').addEventListener('change', function() {
    const label = this.nextElementSibling;
    const fileName = this.files[0] ? this.files[0].name : "Choisir une image";
    label.innerText = fileName;
});

document.getElementById('imageUpload').addEventListener('change', function() {
    const label = this.nextElementSibling;
    const fileName = this.files[0] ? this.files[0].name : "Choisir une image";
    label.innerText = fileName;

    if (this.files.length > 0) {
        this.parentElement.classList.add('filled');
    } else {
        this.parentElement.classList.remove('filled');
    }
});