import React, {useEffect, useState} from 'react';
import {useDispatch} from "react-redux";
import {useNavigate, useParams} from "react-router-dom";
import AdminHeader from "../../header/AdminHearder";
import SideBar from "../../sidebar/SideBar";
import UserService from "../../service/UserService";
import FileUploadService from "../../service/FileUploadService";

const UserDetail = () => {
    const dispatch = useDispatch();
    const {id} = useParams();
    const navigate = useNavigate()
    const [roles, setRoles] = useState([]);
    const [role, setRole] = useState('');
    const [oneAdminUser, setOneAdminUser] = useState({})
    //user attribute
    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [email, setEmail] = useState('')
    const [phoneNum, setPhoneNum] = useState('')
    const [password, setPassword] = useState('')
    const [address, setAddress] = useState('')
    const [avatar, setAvatar] = useState('')
    const [status, setStatus] = useState()

    const userDetailSelector = useState({})
    // const userDetailSelector = useSelector((state) => state.oneAdminUser);
    // const {
    //     loading,
    //     error,
    //     oneAdminUser
    // } = userDetailSelector;

    useEffect(() => {
        if (id) {
            UserService.getUserInfo(id)
                .then(res => {
                    console.log(res.data)
                    setOneAdminUser(res.data)
                    if (res.data.roles[0].name === 'ROLE_ADMIN') {
                        document.getElementById('optionAdmin').setAttribute('selected', 'selected')
                    } else {
                        document.getElementById('optionUser').setAttribute('selected', 'selected')
                    }
                    if (res.data.status === "ACTIVE") {
                        document.getElementById('optionStatusActive').setAttribute('selected', 'selected')
                        console.log('active')
                    } else {
                        console.log('inactive')
                        document.getElementById('optionStatusInactive').setAttribute('selected', 'selected')

                    }
                })
        }

    }, []);

    const updateUser = () => {

        if(id) {
            const dataPayload = {
                firstName: document.getElementById('firstName').value,
                lastName: document.getElementById('lastName').value,
                email: document.getElementById('email').value,
                phoneNumber: document.getElementById('phoneNumber').value,
                roles: [
                    document.getElementById('role').value
                ],
                address: document.getElementById('address').value,
                avatar: avatar,
                status: document.getElementById('status').value
            }
            UserService.changUserInfo(dataPayload, id)
                .then(res => {
                    console.log(res.data)
                    if (res.status === 200) {
                        navigate('/admin')
                    }

                })
                .catch(err => {
                    console.log(err)
                })
        }
        else  {
            const dataPayload = {
                firstName: firstName,
                lastName: lastName,
                email: email,
                phoneNumber:  document.getElementById('phoneNumber').value,
                password: password,
                roles: [ document.getElementById('role').value],
                address: address,
                avatar: avatar,
                status: document.getElementById('status').value

            }
            console.log(dataPayload)
            UserService.createNewUser(dataPayload)
                .then(res => {
                    console.log(res.data)
                    if(res.status === 201) {
                        navigate('/admin')
                    }

                })
                .catch(err => {
                    console.log(err)

                })
        }

    }

    const onThumbnailChange = (e) => {
        FileUploadService.fileUploadSetup(e)
            .then(res => {
                setAvatar(res.data.message);
            })
            .catch(error => {
                alert(error.response.data.message);
                console.log(error);
            })
    }
    return (
        <div>
            <AdminHeader/>
            <SideBar/>
            <div className="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                <div class="row">
                    <div class="col-md-3 border-right">
                        <div
                            class="d-flex flex-column align-items-center text-center p-3 py-5">
                            <img class="rounded-circle mt-5" width="150px"
                                 src={oneAdminUser.avatar} id="avatar"/><span
                            class="font-weight-bold mt-2">{oneAdminUser.firstName + ' ' + oneAdminUser.lastName}</span><span
                            class="text-black-50"
                            mt-2>{oneAdminUser.email}</span><span> </span>
                            <div className="text-center mt-2">
                                <input className="btn btn-primary profile-button" type="file" onChange={(e) => onThumbnailChange(e)}
                                ></input>
                            </div>

                        </div>
                    </div>
                    <div class="col-md-5 border-right">
                        <div class="p-3 py-5">
                            <div
                                class="d-flex justify-content-between align-items-center mb-3">
                                <h4 class="text-right">Profile Settings</h4>
                            </div>
                            <div class="row mt-2">
                                <div class="col-md-6"><label
                                    class="labels">First name</label><input type="text"
                                                                            id='firstName'
                                                                            onChange={(e) => {
                                                                                setFirstName(e.target.value)
                                                                                console.log(e.target.value)
                                                                            }}
                                                                            class="form-control"
                                                                            defaultValue={oneAdminUser.firstName}/>
                                </div>
                                <div class="col-md-6"><label
                                    class="labels">Last name</label><input type="text"
                                                                           id='lastName'
                                                                           class="form-control"
                                                                           onChange={(e) => {
                                                                               setLastName(e.target.value)
                                                                               console.log(e.target.value)
                                                                           }}
                                                                           defaultValue={oneAdminUser.lastName}
                                />
                                </div>
                            </div>
                            <div className="row mt-2">
                                <div className="col-md-6"><label
                                    className="labels">Email</label><input
                                    id='email'
                                    onChange={(e) => setEmail(e.target.value)}
                                    type="text"
                                    className="form-control"
                                    defaultValue={oneAdminUser.email}/></div>
                                <div className="col-md-6"><label
                                    className="labels">Phone number</label><input
                                    type="text"
                                    id='phoneNumber'
                                    className="form-control"
                                    defaultValue={oneAdminUser.phoneNumber}/>
                                </div>
                            </div>
                            <div className="row mt-2" hidden={id ? true : false}>
                                <div className="col-md-12"><label
                                    className="labels">Password</label><input
                                    onChange={(e) => setPassword(e.target.value)}
                                    type="text"
                                    id='password'
                                    className="form-control"

                                    />
                                </div>
                            </div>
                            <br/>
                            <div className='col-md-5'>
                                <label htmlFor="role">Roles:</label>

                                <select name="role" id="role" class="form-select "
                                        onClick={(e) => {
                                            setRole(e.target.value)
                                            console.log(e.target.value)
                                        }}
                                >

                                    <option id='optionAdmin' value='admin'>ADMIN
                                    </option>
                                    <option id='optionUser' value='user'>USER
                                    </option>
                                </select>
                            </div>
                            <div className="row mt-2">
                                <div className="col-md-14"><label
                                    className="labels">Addess</label><textarea
                                    defaultValue={oneAdminUser.address}
                                    onChange={(e) => setAddress(e.target.value)}
                                    id="address" name="publicinfo" cols="40" rows="2"
                                    className="form-control"></textarea></div>
                            </div>
                            <br/>

                            <div className='col-md-5'>
                                <label htmlFor="role">Status:</label>

                                <select name="status" id="status" class="form-select"
                                        onChange={(e) => {
                                            setStatus(e.target.value)
                                            console.log(e.target.value)
                                        }}
                                >
                                    <option id='optionStatusActive' defaultValue='ACTIVE'
                                    >
                                        ACTIVE
                                    </option>

                                    <option id='optionStatusInactive'
                                            defaultValue='INACTIVE'>INACTIVE
                                    </option>
                                </select>
                            </div>

                            <div class="mt-5 text-center">
                                <button class="btn btn-primary profile-button"
                                        onClick={() => updateUser()}
                                        type="button">Save Profile
                                </button>
                                <button className="ms-3 btn-secondary btn btn-primary profile-button" onClick={event => {navigate('/admin')}}
                                        type="button">Back
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )

};

export default UserDetail;
