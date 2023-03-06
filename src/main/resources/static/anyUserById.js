async function getMyUser({id}) {

    const res = await fetch('http://localhost:8080/users/id=${id}')

    const resUser = await res.json();
    console.log(resUser);
     findUserToHTML(resUser)
}

window.addEventListener('DOMContentLoaded', getMyUser);


function findUserToHTML({id, username, email, password, role}) {
    const userList = document.getElementById('anyUserById');

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
