import { useState } from "react";
import { useNavigate } from "react-router-dom";

const SignIn = () => {
  // 훅 선언부
  const navigate = useNavigate();

  const [memberEmail, setMemberEmail] = useState("");
  const [memberPassword, setMemberPassword] = useState("");

  // 메서드 선언부
  /** 로그인 버튼 클릭 시 실행 */
  const doSignIn = () => {
    const member = {
      memberEmail: memberEmail,
      memberPassword: memberPassword,
    };
    console.log(member);
    /**
     *
     *
     * 로그인 api 연결 필요
     *
     *
     */
    navigate("/");
  };

  return (
    <>
      <div>
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
      </div>
      <button onClick={doSignIn}>로그인</button>
    </>
  );
};

export default SignIn;
