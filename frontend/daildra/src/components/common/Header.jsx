import { Link } from "react-router-dom";
import styles from "./Header.module.css";
import { SUB_COLOR } from "constants/colors";

const Header = () => {
  return (
    <div className={styles.wrapper} style={{ backgroundColor: `white` }}>
      <div className={styles.content}>
        <Link to="/" className={styles.logo}>
          daildra
        </Link>
        <Link
          to="/account/sign-in"
          className={styles.btn_sign_in}
          style={{ color: SUB_COLOR, border: `1px solid ${SUB_COLOR}` }}
        >
          로그인
        </Link>
      </div>
    </div>
  );
};

export default Header;
