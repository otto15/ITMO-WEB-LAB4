export default class AuthValidationResult {
  constructor(msgForUsername, msgForPassword, msgForRepeatedPassword) {
    this.msgForUsername = msgForUsername;
    this.msgForPassword = msgForPassword;
    this.msgForRepeatedPassword = msgForRepeatedPassword;
  }
}
