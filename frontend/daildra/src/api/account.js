import { apiInstance as api } from "./http";

const checkAuthUser = () => {
  // const userInfo =
  let accessToken = sessionStorage.getItem("access-token");

  /** 스토어에서 가져온 유저정보가 null이 아닐 것
   * 세션에서 가져온 액세스토큰이 null이 아닐 것
   * -> 스토어에 액세스토큰 넣기?
   *
   * return은 스토어에서 가져온 토근이랑 유저인포가 null이 아니면 됨
   */
  //   if ()
};

/** 로그인 */
const logIn = (user, success, fail) => {
  api.post(`/account/log-in`, user).then(success).catch(fail);
};

/** 로그아웃 */
const logOut = () => {};

/** 회원가입 */
const signUp = () => {};

/** 회원탈퇴 */
const signOut = () => {};

export { checkAuthUser, logIn, logOut, signUp, signOut };
