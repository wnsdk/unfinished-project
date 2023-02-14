import { useState } from "react";
import { useNavigate } from "react-router-dom";
import styles from "./SignUp.module.css";

const SignUp = () => {
  // 훅 선언부
  const navigate = useNavigate();

  const [userNickname, setUserNickname] = useState("");
  const [userEmail, setUserEmail] = useState("");
  const [userPassword, setUserPassword] = useState("");
  const [userPasswordCheck, setUserPasswordCheck] = useState("");

  // 메서드 선언부
  /** 회원가입 버튼 클릭 시 실행 */
  const doSignUp = () => {
    const user = {
      userNickname: userNickname,
      userEmail: userEmail,
      userPassword: userPassword,
    };
    console.log(user);

    /**
     *
     *
     * 회원가입 api 연결 필요
     *
     *
     */

    navigate("/");
  };

  return (
    <div className={styles.wrapper}>
      <div>
        <label>
          <div>닉네임</div>
          <input
            type="text"
            onChange={(e) => {
              setUserNickname(e.target.value);
            }}
          />
        </label>
        <label>
          <div>이메일</div>
          <input
            type="text"
            onChange={(e) => {
              setUserEmail(e.target.value);
            }}
          />
        </label>
        <label>
          <div>비밀번호</div>
          <input
            type="password"
            onChange={(e) => {
              setUserPassword(e.target.value);
            }}
          />
        </label>
        <label>
          <div>비밀번호 확인</div>
          <input
            type="password"
            onChange={(e) => {
              setUserPasswordCheck(e.target.value);
            }}
          />
        </label>
      </div>
      <button onClick={doSignUp} className={styles.btn_sign_up}>
        회원가입
      </button>
    </div>
  );
};

export default SignUp;
