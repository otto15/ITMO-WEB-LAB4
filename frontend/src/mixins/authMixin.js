import AuthUser from "@/models/authUser";

export default {
  data() {
    return {
      user: new AuthUser("", "", ""),
      errorMessageForUsername: "",
      errorMessageForPassword: "",
      errorMessageForRepeatedPassword: "",
      loading: false,
      message: "",
    };
  },
};
