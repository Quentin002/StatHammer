const togglePswCreaCom = document.getElementById('togglePswCreaCom');
const pswCreaCom = document.getElementById('pswCreaCom');
togglePswCreaCom.addEventListener('click', function () {
    const type = pswCreaCom.getAttribute('type') === 'password' ? 'text' : 'password';
    pswCreaCom.setAttribute('type', type);
    this.classList.toggle('fa-eye');
    this.classList.toggle('fa-eye-slash');
});
