async function getAllUser() {
    const res = await fetch('http://localhost:8080/users')
    const principal = await fetch('http://localhost:8080/users/home')

    const resUser = await res.json();
    const  resPrincipal = await principal.json();
    resUser.forEach(resUser => listUserToHTML(resUser));
    userNavbarDetails(resPrincipal);

}

window.addEventListener('DOMContentLoaded', getAllUser);

function listUserToHTML({id, username, email, password, role}) {
    const userList = document.getElementById('listUserInput');

    userList.insertAdjacentHTML('beforeend', `

        <tr>
            <td>${id}</td>
            <td>${username}</td>
            <td>${email}</td>
            <td>${password}</td>
            <td>${role}</td> 
            <td>
                <input type="submit" class="btn btn-info js-open-modal" data-modal="data-modal" data-toggle="modal" 
                data-target="#editModalContainer" value="Edit" data-user-id="${id}" id="myBtn">
            </td>
            <td>
                <input type="submit" class="btn btn-danger" data-toggle="modal"
                    data-target="${'#deleteModal' + id}" data-target="#deleteModalContainer" id="idUser"  data-user-id="${id}"
                    value="Delete">
            </td> 
        </tr>
    `);
}

function userNavbarDetails({email, role}) {
    const userList = document.getElementById('myUserDetails');

    userList.insertAdjacentHTML('beforeend', `
        <b> ${email} </b> with roles: <a>${role} </a> 
    `);
}



