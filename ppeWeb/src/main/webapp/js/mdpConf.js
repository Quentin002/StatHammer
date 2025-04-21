document.addEventListener('DOMContentLoaded', function () {
    const password = document.getElementById('pswCreaCom');
    const confirm = document.getElementById('pswConfirm');
    const btnCreerCompte = document.getElementById('btnCreerCompte');

    const letter = document.getElementById("letter");
    const capital = document.getElementById("capital");
    const number = document.getElementById("number");
    const length = document.getElementById("length");

    function validatePasswordRules() {
        const val = password.value;

        // Lowercase
        val.match(/[a-z]/) ? letter.classList.replace("invalid", "valid") : letter.classList.replace("valid", "invalid");

        // Uppercase
        val.match(/[A-Z]/) ? capital.classList.replace("invalid", "valid") : capital.classList.replace("valid", "invalid");

        // Number
        val.match(/[0-9]/) ? number.classList.replace("invalid", "valid") : number.classList.replace("valid", "invalid");

        // Length
        val.length >= 8 ? length.classList.replace("invalid", "valid") : length.classList.replace("valid", "invalid");
    }

    function allRulesValid() {
        return (
            letter.classList.contains("valid") &&
            capital.classList.contains("valid") &&
            number.classList.contains("valid") &&
            length.classList.contains("valid")
        );
    }

    function validatePasswords() {
        const match = password.value === confirm.value;
        const rulesOk = allRulesValid();

        if (match && rulesOk) {
            btnCreerCompte.innerHTML = `
                <td colspan="2" height='100'>
                    <center><input type="submit" id="submitBtn" value="Créer un compte"></center>
                </td>`;
        } else {
            btnCreerCompte.innerHTML = `
                <td colspan="2" height='100'>
                    <center><input type="submit" id="btnDisabled" value="Créer un compte" disabled></center>
                </td>`;
        }
    }

    // Show/hide password tips
    password.onfocus = () => document.getElementById("message").style.display = "block";
    password.onblur = () => document.getElementById("message").style.display = "none";

    password.addEventListener('input', () => {
        validatePasswordRules();
        validatePasswords();
    });

    confirm.addEventListener('input', validatePasswords);
});
