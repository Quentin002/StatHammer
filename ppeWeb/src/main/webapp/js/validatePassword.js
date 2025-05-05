document.addEventListener("DOMContentLoaded", function () {
  const passwordInput = document.getElementById("pswCreaCom");

  const letter = document.getElementById("letter");
  const capital = document.getElementById("capital");
  const number = document.getElementById("number");
  const length = document.getElementById("length");

  passwordInput.onkeyup = function () {
    const value = passwordInput.value;

    // Validate lowercase letters
    const lowerCaseLetters = /[a-z]/g;
    if (lowerCaseLetters.test(value)) {
      letter.classList.remove("invalid");
      letter.classList.add("valid");
    } else {
      letter.classList.remove("valid");
      letter.classList.add("invalid");
    }

    // Validate capital letters
    const upperCaseLetters = /[A-Z]/g;
    if (upperCaseLetters.test(value)) {
      capital.classList.remove("invalid");
      capital.classList.add("valid");
    } else {
      capital.classList.remove("valid");
      capital.classList.add("invalid");
    }

    // Validate numbers
    const numbers = /[0-9]/g;
    if (numbers.test(value)) {
      number.classList.remove("invalid");
      number.classList.add("valid");
    } else {
      number.classList.remove("valid");
      number.classList.add("invalid");
    }

    // Validate length
    if (value.length >= 8) {
      length.classList.remove("invalid");
      length.classList.add("valid");
    } else {
      length.classList.remove("valid");
      length.classList.add("invalid");
    }
  };
});
