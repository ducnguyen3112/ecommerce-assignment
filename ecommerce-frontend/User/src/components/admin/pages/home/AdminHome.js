import React, {useEffect, useState} from 'react';
import SideBar from "../../sidebar/SideBar";
import AdminHeader from "../../header/AdminHearder";
import {useLocation, useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import adminUserAction from "../../../../redux/actions/AdminUsers";
import DataTable from 'react-data-table-component';

const AdminHome = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    let location = useLocation();
    const [rowId, setRowId] = useState(0);
    const redirect = location.search ? location.search.split("=")[1] : "/admin/login";


    useEffect(() => {
        if (localStorage.getItem("userInfo") == null) {
            navigate(redirect);
        }
    }, [navigate, redirect])
    useEffect(() => {
        dispatch(adminUserAction.getAdminUser());
    }, [dispatch, rowId]);
    const userList = useSelector((state) => state.adminUsers);
    const {loading, errror, adminUsers} = userList;

    function handleEditClick(row) {
        navigate('/admin/users/' + row.id)
    }

    const handleDeleteClick = (row) => {
        dispatch(adminUserAction.deleteAdminUser(row.id));
        dispatch(adminUserAction.getAdminUser());
        setRowId(row.id)

    }

    const columns = [
        {
            name: "Id",
            selector: row => row.id,
            sortable: true
        },
        {
            name: "First name",
            selector: row => row.firstName,
            sortable: true
        },
        {
            name: "Last name",
            selector: row => row.lastName,

            sortable: true,
        },
        {
            name: "Email",
            selector: row => row.email,
            sortable: true,
        },
        {
            name: "Status",
            selector: row => row.status,
            sortable: true,
        },
        {
            name: 'Action',
            button: true,
            width: 'lg',
            cell: (row) => (
                <div>
                    <button className={'btn btn-info '}
                            onClick={handleEditClick.bind(this, row)}
                    >
                        Edit
                    </button>
                    <button className={"btn btn-danger "}
                            onClick={handleDeleteClick.bind(this, row)}>
                        Delete
                    </button>
                </div>
            )
        },

    ];
    console.log(adminUsers.data)

    return (
        <>
            <div>
                <AdminHeader/>
                <SideBar/>

                <main className="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                    <button className='btn btn-info' onClick={() => navigate('/admin/users')}>New User</button>
                    <DataTable
                        columns={columns}
                        data={adminUsers.data}
                        defaultSortField="id"
                        progressPending={loading}
                    />
                </main>

            </div>

        </>
    );
};

export default AdminHome;
