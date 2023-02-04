import { useState } from "react";
import { useNavigate } from "react-router-dom";
import styles from "./SignUp.module.css";

const SignUp = () => {
  // 훅 선언부
  const navigate = useNavigate();

  const [memberNickname, setMemberNickname] = useState("");
  const [memberEmail, setMemberEmail] = useState("");
  const [memberPassword, setMemberPassword] = useState("");
  const [memberPasswordCheck, setMemberPasswordCheck] = useState("");

  // 메서드 선언부
  /** 회원가입 버튼 클릭 시 실행 */
  const doSignUp = () => {
    const member = {
      memberNickname: memberNickname,
      memberEmail: memberEmail,
      memberPassword: memberPassword,
    };
    console.log(member);

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
              setMemberNickname(e.target.value);
            }}
          />
        </label>
        <label>
          <div>이메일</div>
          <input
            type="text"
            onChange={(e) => {
              setMemberEmail(e.target.value);
            }}
          />
        </label>
        <label>
          <div>비밀번호</div>
          <input
            type="password"
            onChange={(e) => {
              setMemberPassword(e.target.value);
            }}
          />
        </label>
        <label>
          <div>비밀번호 확인</div>
          <input
            type="password"
            onChange={(e) => {
              setMemberPasswordCheck(e.target.value);
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
