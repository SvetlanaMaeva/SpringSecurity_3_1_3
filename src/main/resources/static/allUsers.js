async function getAllUser() {
    const res = await fetch('http://localhost:8080/users')
    const principal = await fetch('http://localhost:8080/users/home')

    const resUser = await res.json();
    const resPrincipal = await principal.json();

    resUser.forEach(resUser => listUserToHTML(resUser));
    userNavbarDetails(resPrincipal);




    // let buttons = await document.querySelectorAll('input[data-user-id]');
    // console.log(buttons);
    //
    // for (let button of buttons) {
    //     button.addEventListener('click', async (e) => {
    //         showData(e.currentTarget.dataset.userId)
    //         console.log(findByUserId(button));
    //         // await editModal(button);
    //     });
    //
    // }

}

window.addEventListener('DOMContentLoaded', getAllUser);

let usersInfo = '';
function listUserToHTML({id, username, email, password, role}) {
    const userList = document.getElementById('listUserInput');

    userList.insertAdjacentHTML('beforeend', usersInfo += `

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
    document.getElementById("listUserInput").innerHTML = usersInfo
}

function userNavbarDetails({email, role}) {
    const userList = document.getElementById('myUserDetails');

    userList.insertAdjacentHTML('beforeend', `
        <b> ${email} </b> with roles: <a>${role} </a> 
    `);
}

function showData(id) {
    console.log(id);
}

// async function findByUserId(id) {
//     const response = await fetch(`http://localhost:8080/users/${id}`)
//         .then(data => data.json())
//         .catch(e => null);
//     return await response;
// }

async function editModal(arr, id) {
    console.log(arr);
    const modalEl = await document.getElementById('editModalContainer');
    const user = findByUserId(id);
    console.log(user)

    // modalEl.addEventListener('show.bs.modal', async function (event) {
    //     let id = event.relatedTarget;
    //     let isUser = id.getAttribute('data-user-id');
    //     console.log(isUser);
    //
    // });
}
