import { Link, Route, Routes } from "react-router-dom";
import LogIn from "components/account/LogIn";
import SignUp from "components/account/SignUp";
import styles from "./AccountPage.module.css";
import { SUB_COLOR } from "constants/colors";

const AccountPage = () => {
  return (
    <div className={styles.wrapper} style={{ backgroundColor: `${SUB_COLOR}` }}>
      <div className={styles.container}>
        <Link to="log-in">로그인</Link>
        <Link to="sign-up">회원가입</Link>
        <Routes>
          <Route path="log-in" element={<LogIn />} />
          <Route path="sign-up" element={<SignUp />} />
        </Routes>
      </div>
    </div>
  );
};

export default AccountPage;
