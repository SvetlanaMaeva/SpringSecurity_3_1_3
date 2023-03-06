async function getAllUser() {
    const res = await fetch('http://localhost:8080/users')

    const resUser = await res.json();
    console.log(resUser);

    resUser.forEach(resUser => listUserToHTML(resUser))
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
                <input type="submit" class="btn btn-info" data-toggle="modal"
                     data-target="#editModal"  data-user-id="${id}"
                    value="Edit">
                   
                    
            </td>
            <td>
                <input type="submit" class="btn btn-danger" data-toggle="modal"
                    data-target="${'#deleteModal' + id}" data-target="#editModal"  data-user-id="${id}"
                    value="Delete">
            </td>
                                                            
        </tr>

    `);
}
