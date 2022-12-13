import DotValidationResult from "@/models/dotValidationResult";

class DotValidator {
  validateDot(dot) {
    let msgForX;
    if (dot.x === undefined || dot.x === null) {
      msgForX = "X is required!";
    }

    let msgForY;
    if (dot.y !== undefined && dot.y !== null && !(dot.y.trim() === "")) {
      let y_value = Number(dot.y);
      if (isNaN(y_value)) {
        msgForY = "Y must be a number!";
      } else {
        if (!(y_value > -5 && y_value < 5)) {
          msgForY = "Y must be in (-5, 5) range";
        }
      }
    } else {
      msgForY = "Y is required!";
    }

    return new DotValidationResult(msgForX, msgForY);
  }
}

export default new DotValidator();
