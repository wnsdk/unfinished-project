import { Outlet } from 'react-router-dom';
import styles from './Header.module.css';

export default function Header() {
    return (
        <>
            <div className={styles.header}>Header</div>
            <Outlet />
        </>
    );
}
