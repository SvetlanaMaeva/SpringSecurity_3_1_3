$(async function () {
    await showUsersTable();
});

async function showUsersTable() {
    const userList = $('#listUserInput');
    userList.empty();
    fetch('http://localhost:8080/api')
        .then(r => r.json())
        .then(data => {
            data.forEach(user => {
                let users = `$(
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.password}</td>
                        <td>${user.role}</td>
                        <td>
                            <input type="submit" class="btn btn-info js-open-modal" data-modal="data-modal" data-toggle="modal"
                            data-target="#editModalContainer" value="Edit" data-user-id="${user.id}" id="myBtn">
                        </td>
                        <td>
                            <input type="submit" class="btn btn-danger" data-toggle="modal"
                                id="openDeleteModal" data-target="#deleteModalContainer"  data-user-delete-id="${user.id}"
                                value="Delete">
                        </td>
                    </tr>)`;
                userList.append(users);
            })
        })
}




