import AuthValidationResult from "@/models/authValidationResult";

class AuthValidator {
  validateAuthUser(authUser) {
    let msgForUsername;
    if (authUser.username === null || authUser.username.trim() === "") {
      msgForUsername = "Username is required!";
    }

    let msgForPassword;
    if (authUser.password === null || authUser.password.trim() === "") {
      msgForPassword = "Password is required!";
    }
    if (authUser.password.length < 6 || authUser.password.length > 40) {
      msgForPassword = "Password length must be between 6 and 40";
    }

    let msgForRepeatedPassword;
    if (
      authUser.repeatedPassword === null ||
      authUser.repeatedPassword.trim() === ""
    ) {
      msgForRepeatedPassword = "Repeated password is required!";
    } else if (authUser.repeatedPassword !== authUser.password) {
      msgForRepeatedPassword = "Passwords don't match!";
    }

    return new AuthValidationResult(
      msgForUsername,
      msgForPassword,
      msgForRepeatedPassword
    );
  }
}

export default new AuthValidator();
