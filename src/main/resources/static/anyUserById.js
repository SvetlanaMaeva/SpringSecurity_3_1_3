async function getMyUser() {

    const res = await fetch('http://localhost:8080/api/home')
    const resUser = await res.json();

    findUserToHTML(resUser)
    userNavbarDetails(resUser);
}

window.addEventListener('DOMContentLoaded', getMyUser);

function findUserToHTML({id, username, email, password, role}) {
    const userList = document.getElementById('myUserInfo');

    userList.insertAdjacentHTML('beforeend', `

        <tr>
            <td>${id}</td>
            <td>${username}</td>
            <td>${email}</td>
            <td>${password}</td>
            <td>${role}</td>               
        </tr>
    `);
}

function userNavbarDetails({email, role}) {
    const userList = document.getElementById('myUserDetails');
    userList.insertAdjacentHTML('beforeend', `
        <b> ${email} </b> with roles: <a>${role} </a> 
    `);
}




