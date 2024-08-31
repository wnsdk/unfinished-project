import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { logIn } from "api/account";

const LogIn = () => {
  // 훅 선언부
  const navigate = useNavigate();

  const [userId, setUserId] = useState("");
  const [userPassword, setUserPassword] = useState("");

  // 메서드 선언부
  /** 로그인 버튼 클릭 시 실행 */
  const doLogIn = () => {
    const user = {
      userId: userId,
      userPassword: userPassword,
    };

    logIn(
      user,
      () => {
        navigate("/");
      },
      () => {}
    );
  };

  return (
    <>
      <div>
        <label>
          <div>아이디</div>
          <input
            type="text"
            onChange={(e) => {
              setUserId(e.target.value);
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
      </div>
      <button onClick={doLogIn}>로그인</button>
    </>
  );
};

export default LogIn;
