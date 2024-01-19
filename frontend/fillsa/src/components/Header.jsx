import { Outlet } from 'react-router-dom';

export default function Header() {
    console.log()
    return (
        <>
            <div>Header</div>
            <Outlet />
        </>
    );
}
